package piece;

import controller.Board;
import controller.Position;

public class Bishop extends Piece{
    public Bishop(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♗":"♝");
    }

    @Override
    public Boolean isValidMove(Position newPosition) {
        if (!super.isValidMove(newPosition))return false;
        if (Math.abs(newPosition.getRow() - position.getRow()) == Math.abs(newPosition.getCol() - position.getCol()) && isExistBetween){
            return true;
        } else return false;
    }

    @Override
    public void move(Board board, Position newPosition) {
        if (isValidMove(newPosition)) {
            board.setPiece(this, newPosition);
            super.position = newPosition;
        }
    }
}
