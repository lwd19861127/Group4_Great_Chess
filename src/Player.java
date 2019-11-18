public class Player {
    private Boolean isWhite;

    public Player(Boolean isWhite) {
        this.isWhite = isWhite;
    }

    public Boolean isWhite() {
        return isWhite;
    }

    public void setWhite(Boolean white) {
        isWhite = white;
    }
}
