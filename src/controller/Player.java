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

}
