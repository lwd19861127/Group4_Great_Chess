public enum PieceEnum {
    KING("king"),
    QUEEN("queen"),
    ROOK("rook"),
    BISHOP("bishop"),
    KNIGHT("knight"),
    PAWN("pawn"),
    NONE("none"),
    ;

    public final String name;

    PieceEnum(String name) {
        this.name = name;
    }

    public static Boolean validPromotion(String name) {
        for (PieceEnum piece : PieceEnum.values()) {
            if(QUEEN.getString().equals(name) ||
                    ROOK.getString().equals(name) ||
                    BISHOP.getString().equals(name) ||
                    KNIGHT.getString().equals(name)) return true;
        }
        return false;
    }


    public final String getString() {
        return this.name;
    }

    public static PieceEnum get(String name) {
        for (PieceEnum piece : PieceEnum.values()) {
            if(piece.getString().equals(name)) return piece;
        }
        return QUEEN;
    }

}
