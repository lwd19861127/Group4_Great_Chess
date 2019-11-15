package piece;

import controller.Board;
import controller.Position;

public abstract class Piece {

    protected Position position;
    protected Boolean isWhite;
    protected Boolean isMoved;
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

    public Boolean getMoved() {
        return isMoved;
    }

    public void setMoved(Boolean moved) {
        isMoved = moved;
    }
    public Boolean isValidPickup(Boolean isWhiteByPlayer) {
        return isWhite == isWhiteByPlayer;
    }

    public Boolean isValidMove(Position newPosition, Boolean isNewPositionSameColor, Boolean isExistBetween) {
        return newPosition.getRow() > Board.MIN_BOARD_ROW - 2 && newPosition.getCol() > Board.MIN_BOARD_COL - 2
                && newPosition.getRow() < Board.MAX_BOARD_ROW && newPosition.getCol() < Board.MAX_BOARD_COL
                && !isNewPositionSameColor && !isExistBetween;
    }

    public void move(Board board, Position newPosition) {
        position = newPosition;
        isMoved = true;
    }
}
