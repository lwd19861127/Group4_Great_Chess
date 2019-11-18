package piece;

public class Queen extends Piece{
    public Queen(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♔":"♚");
    }
    @Override
    public Boolean isValidMove(Position newPosition,
                               Boolean isNewPositionSameColor,
                               Boolean isExistBetween,
                               Boolean isNotExistEnemyOnNewPosition) {
        if (!super.isValidMove(newPosition, isNewPositionSameColor, isExistBetween, isNotExistEnemyOnNewPosition)) return false;
        if ((Math.abs(newPosition.getRow() - position.getRow()) == Math.abs(newPosition.getCol() - position.getCol())||
                     newPosition.getCol() == position.getCol() && newPosition.getRow() != position.getRow() ||
                     newPosition.getCol() != position.getCol() && newPosition.getRow() == position.getRow())
                     && !isExistBetween) return true;
        else return false;
    }
}
