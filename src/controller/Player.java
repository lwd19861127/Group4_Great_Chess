package controller;

import piece.Piece;

import java.util.Scanner;

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

    public void makeMove() {

    }

    public Position pickupPosition() {
        String pick = getUserStringInput("Which piece would you pick up?");
        Position  pickupPosition = getPosition(pick);
        return pickupPosition;
    }

    public Position putPosition() {
        String put = getUserStringInput("Where would you like move to?");
        Position putPosition = getPosition(put);
        return putPosition;
    }

    private static String getUserStringInput(String prompt) {
        try {
            System.out.println(prompt);
            Scanner in = new Scanner(System.in);
            return in.nextLine();
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    private static Position getPosition(String value) {
        String string1 = value.substring(0,1);
        String string2 = value.substring(1,2);
        while (!((string1.equals("a") || string1.equals("b")
                || string1.equals("c") || string1.equals("d")
                || string1.equals("e") || string1.equals("f")
                || string1.equals("g") || string1.equals("h"))
                &&
                (string2.equals("1") || string2.equals("2")
                || string2.equals("3") || string2.equals("4")
                || string2.equals("5") || string2.equals("6")
                || string2.equals("7") || string2.equals("8")))) {
            value = getUserStringInput("Invalid input, Which piece would you pick up?");
            string1 = value.substring(0,1);
            string2 = value.substring(1,2);
        }
        int int1 =0;
        int int2 =0;
        if (string1.equals("a")) {
                int2 = 0;
            } else if (string1.equals("b")) {
                int2 = 1;
            } else if (string1.equals("c")) {
                int2 = 2;
            } else if (string1.equals("d")) {
                int2 = 3;
            } else if (string1.equals("e")) {
                int2 = 4;
            } else if (string1.equals("f")) {
                int2 = 5;
            } else if (string1.equals("g")) {
                int2 = 6;
            } else if (string1.equals("h")) {
                int2 = 7;
            }

            if (string2.equals("1")) {
                int1 = 0;
            } else if (string2.equals("2")) {
                int1 = 1;
            } else if (string2.equals("3")) {
                int1 = 2;
            } else if (string2.equals("4")) {
                int1 = 3;
            } else if (string2.equals("5")) {
                int1 = 4;
            } else if (string2.equals("6")) {
                int1 = 5;
            } else if (string2.equals("7")) {
                int1 = 6;
            } else if (string2.equals("8")) {
                int1 = 7;
            }
            return new Position(int1, int2);
    }
}
