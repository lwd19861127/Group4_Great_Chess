import controller.Position;
import controller.Player;
import piece.*;

import java.util.Scanner;

public class Game {
    private static String white = "White";
    private static String black = "Black";
    private static String blackWin = "BlackWin";
    private static String whiteWin = "WhiteWin";
    private static String blackInCheck = "BlackInCheck";
    private static String whiteInCheck = "WhiteInCheck";
    private static String blackCheckmated = "BlackCheckmated";
    private static String whiteCheckmated = "WhiteCheckmated";
    private static String none = "None";

    private Piece[][] board;
    private Player[] players;
    private String turn;
    private String result;
    private String checkStatus;

    public Game() {
        board = new Piece[8][8];
        players = new Player[2];
        players[1] = new Player(white);
        players[2] = new Player(black);
        turn = white;
        checkStatus = none;
    }

    public void creat() {
        initGame();
        //startGame();
    }

    private void initGame() {
        Game game = new Game();
        Piece rookBlack1 = new Rook(new Position(7, 0), false);
        Piece knightBlack1 = new Knight(new Position(7, 1), false);
        Piece bishopBlack1 = new Bishop(new Position(7, 2), false);
        Piece kingBlack = new King(new Position(7, 3), false);
        Piece queenBlack = new Queen(new Position(7, 4), false);
        Piece bishopBlack2 = new Bishop(new Position(7, 5), false);
        Piece knightBlack2 = new Knight(new Position(7, 6), false);
        Piece rookBlack2= new Rook(new Position(7, 7), false);
        Piece pawnBlack1 = new Pawn(new Position(6, 0) ,false);
        Piece pawnBlack2 = new Pawn(new Position(6, 1) ,false);
        Piece pawnBlack3 = new Pawn(new Position(6, 2) ,false);
        Piece pawnBlack4 = new Pawn(new Position(6, 3) ,false);
        Piece pawnBlack5 = new Pawn(new Position(6, 4) ,false);
        Piece pawnBlack6 = new Pawn(new Position(6, 5) ,false);
        Piece pawnBlack7 = new Pawn(new Position(6, 6) ,false);
        Piece pawnBlack8 = new Pawn(new Position(6, 7) ,false);

        Piece pawnWhite1 = new Pawn(new Position(1, 0), true);
        Piece pawnWhite2 = new Pawn(new Position(1, 1), true);
        Piece pawnWhite3 = new Pawn(new Position(1, 2), true);
        Piece pawnWhite4 = new Pawn(new Position(1, 3), true);
        Piece pawnWhite5 = new Pawn(new Position(1, 4), true);
        Piece pawnWhite6 = new Pawn(new Position(1, 5), true);
        Piece pawnWhite7 = new Pawn(new Position(1, 6), true);
        Piece pawnWhite8 = new Pawn(new Position(1, 7), true);

        Piece rookWhite1 = new Rook(new Position(0, 0),true);
        Piece knightWhite1 = new Knight(new Position(0, 1), true);
        Piece bishopWhite1 = new Bishop(new Position(0, 2), true);
        Piece kingWhite = new King(new Position(0, 3), true);
        Piece queenWhite = new Queen(new Position(0, 4), true);
        Piece bishopWhite2 = new Bishop(new Position(0, 5), true);
        Piece knightWhite2 = new Knight(new Position(0, 6), true);
        Piece rookWhite2 = new Rook(new Position(0, 7),true);
        board[7][0] = rookBlack1;
        board[7][1] = knightBlack1;
        board[7][2] = bishopBlack1;
        board[7][3] = kingBlack;
        board[7][4] = queenBlack;
        board[7][5] = bishopBlack2;
        board[7][6] = knightBlack2;
        board[7][7] = rookBlack2;
        board[6][0] = pawnBlack1;
        board[6][1] = pawnBlack2;
        board[6][2] = pawnBlack3;
        board[6][3] = pawnBlack4;
        board[6][4] = pawnBlack5;
        board[6][5] = pawnBlack6;
        board[6][6] = pawnBlack7;
        board[6][7] = pawnBlack8;

        board[1][0] = pawnWhite1;
        board[1][1] = pawnWhite2;
        board[1][2] = pawnWhite3;
        board[1][3] = pawnWhite4;
        board[1][4] = pawnWhite5;
        board[1][5] = pawnWhite6;
        board[1][6] = pawnWhite7;
        board[1][7] = pawnWhite8;
        board[0][0] = rookWhite1;
        board[0][1] = knightWhite1;
        board[0][2] = bishopWhite1;
        board[0][3] = kingWhite;
        board[0][4] = queenWhite;
        board[0][5] = bishopWhite2;
        board[0][6] = knightWhite2;
        board[0][7] = rookWhite2;

        for (int i = board.length; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getShape());
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    private void startGame() {

    }
    private void pickUpPiece() {
        String pieceName = getUserStringInput("Which piece would you like to move? " +
        "\nKing, Queue, Bishop, Knight, Rook, Pawn?");
    }

    private static String getUserStringInput(String prompt) {
        try {
            System.out.println(prompt);
            Scanner in = new Scanner(System.in);
            return in.nextLine();
        } catch (Exception e) {
            return "";
        }
    }
}
