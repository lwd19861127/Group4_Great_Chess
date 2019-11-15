package piece;

import controller.Board;
import controller.Position;

public class Rook extends Piece{
    public Rook(Position position, Boolean isWhite) {
        super(position, isWhite, isWhite?"♖":"♜");
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
        // It's not same position
        // When Row is same position, Col can be anywhere
        // When Col is same position, Row can be anywhere
        if (!(newRow == oldRow && newCol == oldCol)
                && ((newRow == oldRow && newCol != oldCol)
                || newCol == oldCol && newRow != oldCol)) {
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
