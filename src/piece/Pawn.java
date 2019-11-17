package piece;

import controller.Board;
import controller.Position;

public class Pawn extends Piece{
    public boolean isAfterMoveTwo = false;

    public Pawn(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♙":"♟");
    }

    @Override
    public Boolean isValidMove(Position newPosition,
                       Boolean isNewPositionSameColor,
                       Boolean isExistBetween,
                       Boolean isNotExistEnemyOnNewPosition) {
        if (!super.isValidMove(newPosition,
                isNewPositionSameColor,
                isExistBetween,
                isNotExistEnemyOnNewPosition)) {
            return false;
        }
        int moveRow = newPosition.getRow() - position.getRow();
        if (isExistBetween ||
                (isNotExistEnemyOnNewPosition && position.getCol() != newPosition.getCol()) ||
                (!isMoved && Math.abs(moveRow) > 2) ||
                (isMoved && Math.abs(moveRow) > 1) ||
                (isWhite && moveRow < 0) ||
                (!isWhite && moveRow > 0)) {
            return false;
        }
        return true;
    }

    @Override
    public void move(Position newPosition) {
        if (Math.abs(position.getRow() - newPosition.getRow()) == 2) {
            isAfterMoveTwo = true;
        } else if (isAfterMoveTwo) {
            isAfterMoveTwo = false;
        }
        super.move(newPosition);
    }
}
