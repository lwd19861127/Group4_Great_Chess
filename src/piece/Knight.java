package piece;

public class Knight extends Piece{
    public Knight(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♘":"♞");
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
        if ((Math.abs(newRow - oldRow) == 2 && Math.abs(newCol - oldCol) == 1)
                || (Math.abs(newRow - oldRow) == 1 && Math.abs(newCol - oldCol) == 2)) {
            return true;
        }
        return false;
    }
}
