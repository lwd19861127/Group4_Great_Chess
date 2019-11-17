package piece;

import controller.Board;
import controller.Position;

public class Knight extends Piece{
    public Knight(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♘":"♞");
    }

    @Override
    public Boolean isValidMove(Position newPosition) {
        if (!super.isValidMove(newPosition)) {
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

    @Override
    public void move(Board board, Position newPosition) {
        if (isValidMove(newPosition)) {
            board.setPiece(this, newPosition);
            super.position = newPosition;
        }
    }
}
