package controller;

import piece.*;

import java.util.Arrays;
import java.util.List;

public class Board {
    public final static int NORMAL_KING_NORMAL = 2;
    public final static int MAX_BOARD_ROW = 8;
    public final static int MAX_BOARD_COL = 8;
    public final static int MIN_BOARD_ROW = 1;
    public final static int MIN_BOARD_COL = 1;
    public final static List<String> POSITION_COLS = Arrays.asList("a","b","c","d","e","f","g","h");
    public final static List<String> POSITION_ROWS = Arrays.asList("1","2","3","4","5","6","7","8");

    public Boolean isExistWhiteKing = true;
    private Piece[][] board = new Piece[MAX_BOARD_ROW][MAX_BOARD_COL];

    public Boolean getExistWhiteKing() {
        return isExistWhiteKing;
    }
    public Piece getPiece(Position position) {
        return board[position.getRow()][position.getCol()];
    }

    public void setPiece(Piece piece, Position newPosition) {
        this.board[piece.getPosition().getRow()][piece.getPosition().getCol()] =
                new None(new Position(piece.getPosition().getRow(), piece.getPosition().getCol()),true);;
        this.board[newPosition.getRow()][newPosition.getCol()] = piece;
    }

    public Boolean isCapturedKing() {
        int kingCount = 0;
        for (int row=MIN_BOARD_ROW-1;row<MAX_BOARD_ROW;row++) {
            for (int col=MIN_BOARD_COL-1;col<MAX_BOARD_COL;col++) {
                if (board[row][col] instanceof King) {
                    kingCount++;
                    isExistWhiteKing = board[row][col].isWhite();
                }
            }
        }
        return kingCount != NORMAL_KING_NORMAL;
    }

    public Boolean isValidPickup(Piece pickupPiece, Boolean isPlayerWhite) {
        return pickupPiece instanceof None || !pickupPiece.isValidPickup(isPlayerWhite);
    }

    public Boolean isValidPut(Piece pickupPiece, Piece putPositionPiece, Boolean isPlayerWhite) {
        return pickupPiece.isValidMove(putPositionPiece.getPosition(),
                putPositionPiece instanceof None ? false : putPositionPiece.isWhite() == isPlayerWhite,
                isExistBetween(pickupPiece.getPosition(), putPositionPiece.getPosition()),
                putPositionPiece instanceof None);
    }
    public Boolean isExistBetween(Position pickupPosition , Position putPosition) {
        int upperRow = pickupPosition.getRow() > putPosition.getRow() ? pickupPosition.getRow() : putPosition.getRow();
        int lowerRow = pickupPosition.getRow() < putPosition.getRow() ? pickupPosition.getRow() : putPosition.getRow();
        int upperCol = pickupPosition.getCol() > putPosition.getCol() ? pickupPosition.getCol() : putPosition.getCol();
        int lowerCol = pickupPosition.getCol() < putPosition.getCol() ? pickupPosition.getCol() : putPosition.getCol();
        // move horizontal
        if (upperRow == lowerRow) {
            for (int col = lowerCol+1;col<upperCol;col++) {
                if (!(board[upperRow][col] instanceof None)) return true;
            }
            // move vertical
        } else if (upperCol == lowerCol) {
            for (int row = lowerRow+1;row<upperRow;row++) {
                if (!(board[row][upperCol] instanceof None)) return true;
            }
            // move diagonal
        } else if (Math.abs(upperRow - lowerRow) == Math.abs(upperCol - lowerCol)) {
            // forward to left down
            if (pickupPosition.getRow() > putPosition.getRow() && pickupPosition.getCol() > putPosition.getCol()) {
                int seqCol = putPosition.getCol() + 1;
                    for (int row = putPosition.getRow()+1;row<pickupPosition.getRow();row++) {
                        if (!(board[row][seqCol] instanceof None)) return true;
                        seqCol++;
                    }
            }
            // forward to right up
            else if (pickupPosition.getRow() < putPosition.getRow() && pickupPosition.getCol() < putPosition.getCol()) {
                int seqCol = pickupPosition.getCol() + 1;
                for (int row = pickupPosition.getRow()+1;row<putPosition.getRow();row++) {
                    if (!(board[row][seqCol] instanceof None)) return true;
                    seqCol++;
                }
            }
            // forward to left up
            else if (pickupPosition.getRow() < putPosition.getRow() && pickupPosition.getCol() > putPosition.getCol()) {
                int seqCol = pickupPosition.getCol() - 1;
                for (int row = pickupPosition.getRow()+1;row<putPosition.getRow();row++) {
                    if (!(board[row][seqCol] instanceof None)) return true;
                    seqCol--;
                }
            }
            // forward to right down
            else if (pickupPosition.getRow() > putPosition.getRow() && pickupPosition.getCol() < putPosition.getCol()) {
                int seqCol = putPosition.getCol() - 1;
                for (int row = putPosition.getRow()+1;row<pickupPosition.getRow();row++) {
                    if (!(board[row][seqCol] instanceof None)) return true;
                    seqCol--;
                }
            }
        }
        return false;
    }

    public void enPassant(Piece pickupPiece, Piece putPositionPiece) {
        Pawn behindTwoMovePawn = getBehindTwoMovePawn(pickupPiece.isWhite(), putPositionPiece);
        Piece none = new None(new Position(behindTwoMovePawn.getPosition().getRow(), behindTwoMovePawn.getPosition().getCol()), false);
        setPiece(none, behindTwoMovePawn.getPosition());
    }

    public void move(Piece pickupPiece, Piece putPositionPiece) {
        setPiece(pickupPiece, putPositionPiece.getPosition());
        pickupPiece.move(putPositionPiece.getPosition());

        Boolean canEnPassant = getBehindTwoMovePawn(pickupPiece.isWhite(), putPositionPiece) != null;
        if (canEnPassant) {
            enPassant(pickupPiece, putPositionPiece);
        }
    }

    public void promoteMove(Piece pickupPiece, Piece putPositionPiece, PieceEnum changePiece) {
        Piece newPiece;
        switch (changePiece) {
            case ROOK:
                newPiece = new Rook(new Position(pickupPiece.getPosition().getRow(), pickupPiece.getPosition().getCol()),pickupPiece.isWhite());
                break;
            case BISHOP:
                newPiece = new Bishop(new Position(pickupPiece.getPosition().getRow(), pickupPiece.getPosition().getCol()),pickupPiece.isWhite());
                break;
            case KNIGHT:
                newPiece = new Knight(new Position(pickupPiece.getPosition().getRow(), pickupPiece.getPosition().getCol()),pickupPiece.isWhite());
                break;
            default:
                newPiece = new Queen(new Position(pickupPiece.getPosition().getRow(), pickupPiece.getPosition().getCol()),pickupPiece.isWhite());
                break;
        }
        move(newPiece, putPositionPiece);
    }

    public Pawn getBehindTwoMovePawn(Boolean playerWhite, Piece putPiece) {
        int behindRow = playerWhite ? putPiece.getPosition().getRow()-1:putPiece.getPosition().getRow() +1;
        if (behindRow >= MAX_BOARD_ROW || behindRow < MIN_BOARD_ROW-1) return null;
        Piece behindPiece = board[behindRow][putPiece.getPosition().getCol()];
        if (behindPiece instanceof Pawn) {
            Pawn behindPawn = (Pawn)behindPiece;
            if (behindPawn.isAfterMoveTwo) {
                return behindPawn;
            }
        }
        return null;
    }

    public Boolean canPromote(Piece pickupPiece, Piece putPiece) {
        if (!(pickupPiece instanceof Pawn)) return false;
        if (pickupPiece.isWhite() && putPiece.getPosition().getRow() == MAX_BOARD_ROW-1) return true;
        else if (!pickupPiece.isWhite() && putPiece.getPosition().getRow() == MIN_BOARD_ROW-1) return true;
        else return false;
    }

    public void initBoard() {
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

        for (int row=5;row>1;row--) {
            for (int col=MIN_BOARD_COL-1;col<MAX_BOARD_COL;col++) {
                board[row][col] = new None(new Position(row, col), false);
            }
        }

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
    }

    public void printBoard() {
        String rows = "     a    b    c    d    e    f    g    h \n";
        for (int row = board.length - 1; row >= 0; row--) {
            rows += "  ==========================================\n";
            rows += (row + 1) + " || ";
            String cols = "";
            for (int col = 0; col < board[row].length; col++) {
                cols += board[row][col].getShape() + " || ";
            }
            rows += cols + " " + (row + 1) + "\n";
        }
        rows += "  ==========================================\n";
        rows += "     a    b    c    d    e    f    g    h ";
        System.out.println(rows);
    }
}
