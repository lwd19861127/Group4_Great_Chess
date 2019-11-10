package piece;

import controller.Board;
import controller.Position;

public class King extends Piece {

    public King(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♕":"♛");
    }


    public Boolean isValidMove(Position newPosition) {
        if (!super.isValidMove(newPosition)) {
            return false;
        }
        if ((Math.abs(newPosition.getRow() - position.getRow()) == 1
                || Math.abs(newPosition.getRow() - position.getRow()) == 0)
                && (Math.abs(newPosition.getCol() - position.getCol()) ==1
                || Math.abs(newPosition.getCol() - position.getCol()) ==0)) {
            return true;
        } else {
            return false;
        }
    }

    public void move(Board board, Position newPosition) {
        if (isValidMove(newPosition)) {
            board.setPiece(this, newPosition);
            super.position = newPosition;
        }
    }
}
