package piece;

import controller.Board;
import controller.Position;

public class Knight extends Piece{
    public Knight(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♘":"♞");
    }

    @Override
    public Boolean isValidMove(Position newPosition) {
        return super.isValidMove(newPosition);
    }

    @Override
    public void move(Board board, Position newPosition) {
        if (isValidMove(newPosition)) {
            board.setPiece(this, newPosition);
            super.position = newPosition;
        }
    }
}
