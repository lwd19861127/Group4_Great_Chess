package controller;

import piece.*;

import java.util.Scanner;
public class Game {
    private static String white = "White";
    private static String black = "Black";
    private static String blackWin = "BlackWin";
    private static String whiteWin = "WhiteWin";
    private static String draw = "Draw";
    private static String blackInCheck = "BlackInCheck";
    private static String whiteInCheck = "WhiteInCheck";
    private static String blackCheckmated = "BlackCheckmated";
    private static String whiteCheckmated = "WhiteCheckmated";

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

    public void creat() {
        board.initBoard();
        board.printBoard();
        operation();
    }

    private void operation() {
        while (board.isCapturedKing()) {
            if (isWhiteTurn) {
                System.out.print("Is White's turn, ");
                moveProcess(whitePlayer);
            } else {
                System.out.print("Is Black's turn, ");
                moveProcess(blackPlayer);
            }
            board.printBoard();
        }
            System.out.println("White is win");
        if (board.getExistWhiteKing()) {
        } else {
            System.out.println("Black is win");
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
        while (!pickupPiece.isValidMove(putPositionPiece.getPosition(),
                putPositionPiece instanceof None ? false : putPositionPiece.isWhite() == player.isWhite(),
                board.isExistBetween(pickupPiece.getPosition(), putPositionPiece.getPosition()))) {
            input = getUserStringInput(MESSAGE_INPUT_INVALID + MESSAGE_INPUT_PUT, true);
            if (input.equals(RE_INPUT)) return;
            putPositionPiece = board.getPiece(Position.getPosition(input));
        }
        board.setPiece(pickupPiece, putPositionPiece.getPosition());
        pickupPiece.move(board, putPositionPiece.getPosition());

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
