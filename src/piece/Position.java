package piece;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public static Position getPosition(String input) {
        String string1 = input.substring(0, 1);
        String string2 = input.substring(1, 2);
        int int1 = Integer.parseInt(string2) - 1;
        int int2 = 0;
        switch (string1) {
            case "a":
                int2 = 0;
                break;
            case "b":
                int2 = 1;
                break;
            case "c":
                int2 = 2;
                break;
            case "d":
                int2 = 3;
                break;
            case "e":
                int2 = 4;
                break;
            case "f":
                int2 = 5;
                break;
            case "g":
                int2 = 6;
                break;
            case "h":
                int2 = 7;
                break;
        }
        return new Position(int1, int2);
    }
}
