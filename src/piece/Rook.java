package piece;

public class Rook extends Piece{
    public Rook(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♖":"♜");
    }

    @Override
    public Boolean isValidMove(Position newPosition,
                               Boolean isNewPositionSameColor,
                               Boolean isExistBetween,
                               Boolean isNotExistEnemyOnNewPosition) {
        if (!super.isValidMove(newPosition, isNewPositionSameColor, isExistBetween, isNotExistEnemyOnNewPosition)) {
            return false;
        }
        int newRow = newPosition.getRow();
        int oldRow = position.getRow();
        int newCol = newPosition.getCol();
        int oldCol = position.getCol();
        // It's not same position
        // When Row is same position, Col can be anywhere
        // When Col is same position, Row can be anywhere
        if ((newRow == oldRow || newCol == oldCol) && !isExistBetween) {
            return true;
        }
        return false;
    }
}
