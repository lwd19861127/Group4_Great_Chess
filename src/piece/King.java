package piece;

import controller.Position;

public class King extends Piece {

    public King(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♕":"♛");
    }

    public Boolean isValidMove(Position newPosition,
                               Boolean isNewPositionSameColor,
                               Boolean isExistBetween,
                               Boolean isNotExistEnemyOnNewPosition) {
        if (!super.isValidMove(newPosition, isNewPositionSameColor, isExistBetween, isNotExistEnemyOnNewPosition)) {
            return false;
        }
        if ((Math.abs(newPosition.getRow() - position.getRow()) == 1
                || Math.abs(newPosition.getRow() - position.getRow()) == 0)
                && (Math.abs(newPosition.getCol() - position.getCol()) == 1
                || Math.abs(newPosition.getCol() - position.getCol()) == 0)) {
            return true;
        } else {
            return false;
        }
    }
}
