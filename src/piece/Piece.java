package piece;

import controller.Board;
import controller.Position;

public abstract class Piece {
    protected Position position;
    protected Boolean isWhite;
    protected String shape;

    public Piece(Position position, Boolean isWhite, String shape) {
        this.position = position;
        this.isWhite = isWhite;
        this.shape = shape;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean isWhite() {
        return isWhite;
    }

    public void setWhite(Boolean white) {
        isWhite = white;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Boolean isValidMove(Position newPosition) {
        if (newPosition.getRow() >= 0 && newPosition.getCol() >= 0
            && newPosition.getRow() < 8 && newPosition.getCol() < 8) {
            return true;
        } else {
            return false;
        }
    }

    public abstract void move(Board board, Position newPosition);
}
