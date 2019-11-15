package controller;

import piece.*;

import java.util.Scanner;

public class Game {
    private static String RE_INPUT = "reinput";
    private static String MESSAGE_INPUT_PICKUP = "Which piece would you like to pick up?";
    private static String MESSAGE_INPUT_PUT = "Enter position you wanna put.\nif you want to change picked up piece, Enter \"reinput\"";
    private static String MESSAGE_INPUT_INVALID = "Invalid input, ";
    private static String MESSAGE_WHITE_TURN = "Is White's turn, ";
    private static String MESSAGE_BLACK_TURN = "Is Black's turn, ";
    private static String MESSAGE_WHITE_WIN = "White WIN!";
    private static String MESSAGE_BLACK_WIN = "Black WIN!";

    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Boolean isWhiteTurn;

    public Game() {
        board = new Board();
        whitePlayer = new Player(true);
        blackPlayer = new Player(false);
        isWhiteTurn = true;
    }

    public void create() {
        board.initBoard();
        board.printBoard();
        operation();
    }

    private void operation() {
        while (!board.isCapturedKing()) {
            if (isWhiteTurn) {
                System.out.print(MESSAGE_WHITE_TURN);
                moveProcess(whitePlayer);
            } else {
                System.out.print(MESSAGE_BLACK_TURN);
                moveProcess(blackPlayer);
            }
            board.printBoard();
        }
        if (board.getExistWhiteKing()) {
            System.out.println(MESSAGE_WHITE_WIN);
        } else {
            System.out.println(MESSAGE_BLACK_WIN);
        }
    }

    private void moveProcess(Player player) {
        String input = getUserStringInput(MESSAGE_INPUT_PICKUP, false);
        Piece pickupPiece = board.getPiece(Position.getPosition(input));

        // check can get own piece
        while(pickupPiece instanceof None || !pickupPiece.isValidPickup(player.isWhite())) {
            input = getUserStringInput(MESSAGE_INPUT_INVALID + MESSAGE_INPUT_PICKUP, false);
            pickupPiece = board.getPiece(Position.getPosition(input));
        }

        input = getUserStringInput(MESSAGE_INPUT_PUT, true);
        if (input.equals(RE_INPUT)) return;
        Piece putPositionPiece = board.getPiece(Position.getPosition(input));

        // check input is "reinput" or valid put position
        // when check isNewPositionSameColor(isValidMove's second parameter), if putpossition is NonePiece, set false
        while (!pickupPiece.isValidMove(putPositionPiece.getPosition(),
                putPositionPiece instanceof None ? false : putPositionPiece.isWhite() == player.isWhite(),
                board.isExistBetween(pickupPiece.getPosition(), putPositionPiece.getPosition()),
                putPositionPiece instanceof None)) {
            input = getUserStringInput(MESSAGE_INPUT_INVALID + MESSAGE_INPUT_PUT, true);
            if (input.equals(RE_INPUT)) return;
            putPositionPiece = board.getPiece(Position.getPosition(input));
        }
        board.setPiece(pickupPiece, putPositionPiece.getPosition());
        pickupPiece.move(putPositionPiece.getPosition());

        // change player turn
        isWhiteTurn = !isWhiteTurn;
    }

    private String getUserStringInput(String prompt, Boolean canInputPrevious) {
        try {
            System.out.println(prompt);
            String input = new Scanner(System.in).nextLine();
            if (canInputPrevious && input.equals(RE_INPUT)) {
                return input;
            }
            while (!isValidInput(input)) {
                input = getUserStringInput(MESSAGE_INPUT_INVALID + prompt, canInputPrevious);
            }
            return input;
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    private static boolean isValidInput(String input) {
        if (input.length() < 2) {
            return false;
        }
        String string1 = input.substring(0, 1);
        String string2 = input.substring(1, 2);
        return Board.POSITION_COLS.contains(string1) && Board.POSITION_ROWS.contains(string2);
    }
}
