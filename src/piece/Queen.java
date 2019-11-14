package piece;

import controller.Board;
import controller.Position;

public class Queen extends Piece{
    public Queen(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♔":"♚");
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
        // Move logic bishop
        if (Math.abs(newPosition.getRow() - position.getRow()) == Math.abs(newPosition.getCol() - position.getCol())){
            return true;
        }
        // Move logic rook
        if(newPosition.getCol() == position.getCol() && newPosition.getRow() != position.getRow() ||
           newPosition.getCol() != position.getCol() && newPosition.getRow() == position.getRow()){
            return true;
        }
        else {
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
