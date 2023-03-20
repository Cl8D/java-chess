package chess.domain.chess;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.Map;

public final class ChessGame {
    private final ChessBoard chessBoard;
    private CampType currentCamp;

    public ChessGame() {
        this.chessBoard = ChessBoard.getInstance(this);
    }

    public void setUp(final Position source, final Position target, final CampType currentCamp) {
        this.currentCamp = currentCamp;
        play(source, target);
    }

    private void play(final Position source, final Position target) {
        final Piece piece = chessBoard.checkPiece(source);
        validateCamp(piece);
        validateTargetSameCamp(target);
        validateObstacle(source, target, piece);
        if (validateAttack(source, target, piece)) {
            return;
        }
        validateMove(source, target, piece);
    }

    private void validateObstacle(final Position source, final Position target, final Piece piece) {
        boolean isPossibleRoute = chessBoard.isPossibleRoute(source, target, piece);
        if (!piece.isPossibleRoute(source, target, isPossibleRoute)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validateCamp(final Piece piece) {
        if (!piece.isSameCamp(currentCamp)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다. 현재 차례 = " + currentCamp.name());
        }
    }

    private void validateTargetSameCamp(final Position target) {
        if (chessBoard.contains(target) && chessBoard.checkPiece(target).isSameCamp(currentCamp)) {
            throw new IllegalArgumentException("아군 기물이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private boolean validateAttack(final Position source, final Position target, final Piece piece) {
        if (piece.canAttack(source, target) && chessBoard.contains(target)) {
            movePiece(source, target, piece);
            return true;
        }
        return false;
    }

    private void validateMove(final Position source, final Position target, final Piece piece) {
        if (piece.canMove(source, target)) {
            movePiece(source, target, piece);
        }
    }

    private void movePiece(final Position source, final Position target, final Piece piece) {
        chessBoard.removePiece(source);
        chessBoard.putPiece(target, piece);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getBoard();
    }
}
