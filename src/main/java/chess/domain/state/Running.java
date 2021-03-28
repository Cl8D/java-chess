package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import chess.domain.team.PieceSet;
import chess.domain.team.Score;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Running implements GameState {
    private final Map<Position, Piece> chessBoard;
    private final TeamColor turn;

    public Running(Map<Position, Piece> chessBoard, TeamColor turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    @Override
    public GameState move(Position source, Position target) {
        Piece startPiece = chessBoard.get(source);
        Piece targetPiece = chessBoard.get(target);
        if (startPiece.getColor() != turn) {
            throw new IllegalArgumentException("차례가 아닙니다.");
        }
        if (chessBoard.get(source).isMoveAble(target, chessBoard)) {
            return moveBoard(source, target, startPiece, targetPiece);
        }
        throw new IllegalArgumentException("잘못된 이동입니다.");
    }

    private GameState moveBoard(Position source, Position target, Piece startPiece,
        Piece targetPiece) {
        if (chessBoard.get(target) == Blank.INSTANCE) {
            movePieces(source, target, startPiece);
            return new Running(chessBoard, turn.counterpart());
        }

        targetPiece.dead();
        movePieces(source, target, startPiece);
        return kingCase(targetPiece);
    }

    private GameState kingCase(Piece targetPiece) {
        if (targetPiece.isKing()) {
            return new Finished(chessBoard);
        }
        return new Running(chessBoard, turn.counterpart());
    }

    private void movePieces(Position source, Position target, Piece startPiece) {
        chessBoard.put(source, Blank.INSTANCE);
        chessBoard.put(target, startPiece);
        startPiece.changePosition(target);
    }

    @Override
    public Result result(PieceSet black, PieceSet white) {
        Map<TeamColor, Score> result = teamScores(black, white);

        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) > 0) {
            return new Result(result, TeamColor.BLACK);
        }
        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) < 0) {
            return new Result(result, TeamColor.WHITE);
        }

        return new Result(result, TeamColor.NONE);
    }


    private Map<TeamColor, Score> teamScores(PieceSet black, PieceSet white) {
        Map<TeamColor, Score> result = new HashMap<>();
        result.put(TeamColor.BLACK, black.calculateScore());
        result.put(TeamColor.WHITE, white.calculateScore());
        return result;
    }


    @Override
    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    @Override
    public boolean containsKey(Position position) {
        return chessBoard.containsKey(position);
    }

    @Override
    public GameState terminate() {
        return new Finished(chessBoard);
    }

    @Override
    public boolean isRunning() {
        return true;
    }


}
