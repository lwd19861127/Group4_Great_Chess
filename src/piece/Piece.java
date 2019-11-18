package piece;

public abstract class Piece implements Cloneable {

    public final static int MAX_BOARD_ROW = 8;
    public final static int MAX_BOARD_COL = 8;
    public final static int MIN_BOARD_ROW = 1;
    public final static int MIN_BOARD_COL = 1;

    protected Position position;
    protected Boolean isWhite;
    protected Boolean isMoved = false;
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
     * check common validation for all of pieces
     * 1. move or not
     * 2. move within board
     * 3. destination position's piece is different color
     * NOTE!! isExistBetween and isExistEnemyOnNewPosition are only for specific pieces
     * @param newPosition
     * @param isNewPositionSameColor
     * @param isExistBetween
     * @param isNotExistEnemyOnNewPosition
     * @return
     */
    public Boolean isValidMove(Position newPosition,
                               Boolean isNewPositionSameColor,
                               Boolean isExistBetween,
                               Boolean isNotExistEnemyOnNewPosition) {
        return (position.getRow() != newPosition.getRow() || position.getCol() != newPosition.getCol())
                && newPosition.getRow() > MIN_BOARD_ROW - 2 && newPosition.getCol() > MIN_BOARD_COL - 2
                && newPosition.getRow() < MAX_BOARD_ROW && newPosition.getCol() < MAX_BOARD_COL
                && !isNewPositionSameColor;
    }

    public void move(Position newPosition) {
        position = newPosition;
        isMoved = true;
    }

    @Override
    public Object clone() {
        Object copy = null;
        try {
            copy = (Piece)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
