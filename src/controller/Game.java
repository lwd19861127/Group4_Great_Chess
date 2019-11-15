package controller;

import piece.*;

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
                isWhiteTurn = false;
            } else {
                System.out.print("Is Black's turn, ");
                moveProcess(blackPlayer);
                isWhiteTurn = true;
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
        Position pickupPosition = player.pickupPosition();
        Piece pickupPiece = board.getPiece(pickupPosition);
        while (pickupPiece == null || pickupPiece.isWhite() != player.isWhite()) {
            System.out.print("Invalid input, ");
            pickupPosition = player.pickupPosition();
            pickupPiece = board.getPiece(pickupPosition);
        }
        Position putPosition = player.putPosition();
        Piece putPositionPiece = board.getPiece(putPosition);
        pickupPiece.move(board, putPosition);
    }

        // check input is "reinput" or valid put position
        while (!pickupPiece.isValidMove(putPositionPiece.getPosition(),
                putPositionPiece instanceof None ? false : putPositionPiece.isWhite() == player.isWhite(),
                board.isExistBetween(pickupPiece.getPosition(), putPositionPiece.getPosition()))) {
            input = getUserStringInput(MESSAGE_INPUT_INVALID + MESSAGE_INPUT_PUT, true);
            if (input.equals(RE_INPUT)) return;
            putPositionPiece = board.getPiece(Position.getPosition(input));
        }

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
