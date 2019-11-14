package piece;

import controller.Board;
import controller.Position;

public class Bishop extends Piece{
    public Bishop(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♗":"♝");
    }

    @Override
    public Boolean isValidMove(Position newPosition) {
        // Validation
        if (!super.isValidMove(newPosition)){
            return false;
        }
        // Attempt to move to the same cell
        if (position.getRow() == newPosition.getRow() && position.getCol() == newPosition.getCol())
            return false;
        // Move logic
        if (Math.abs(newPosition.getRow() - position.getRow()) == Math.abs(newPosition.getCol() - position.getCol())){
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void move(Board board, Position newPosition) {
        if (isValidMove(newPosition)) {
            board.setPiece(this, newPosition);
            super.position = newPosition;
        }
    }
}
