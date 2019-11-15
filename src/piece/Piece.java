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

    /**
     * check below
     * 1. move or not
     * 2. move within board
     * 3. destination position's piece is different color
     * @param newPosition
     * @param isNewPositionSameColor
     * @param isExistBetween
     * @return
     */
    public Boolean isValidMove(Position newPosition, Boolean isNewPositionSameColor, Boolean isExistBetween) {
        return (position.getRow() != newPosition.getRow() || position.getCol() != newPosition.getCol())
                && newPosition.getRow() > Board.MIN_BOARD_ROW - 2 && newPosition.getCol() > Board.MIN_BOARD_COL - 2
                && newPosition.getRow() < Board.MAX_BOARD_ROW && newPosition.getCol() < Board.MAX_BOARD_COL
                && !isNewPositionSameColor;
    }

    public void move(Position newPosition) {
        position = newPosition;
        isMoved = true;
    }
}
