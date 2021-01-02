package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {
    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    private final int cachedHashCode;

    Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = false;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode() {
        int result = pieceType.hashCode();

        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove ? 1 : 0);

        return result;
    }

    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Piece)) {
            return false;
        }

        final Piece otherPiece = (Piece) obj;

        return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() &&
                pieceAlliance == otherPiece.getPieceAlliance() && isFirstMove == otherPiece.isFirstMove();
    }

    public int hashCode() {
        return this.cachedHashCode;
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
    public abstract Piece movePiece(Move move);

    public enum PieceType {
        PAWN("P") {
            public boolean isKing() {
                return false;
            }

            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N") {
            public boolean isKing() {
                return false;
            }

            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B") {
            public boolean isKing() {
                return false;
            }

            public boolean isRook() {
                return false;
            }
        },
        ROOK("R") {
            public boolean isKing() {
                return false;
            }

            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q") {
            public boolean isKing() {
                return false;
            }

            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            public boolean isKing() {
                return true;
            }

            public boolean isRook() {
                return false;
            }
        };

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();
    }
}
