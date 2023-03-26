package chess.dao.chess;

import chess.entity.PieceEntity;

import java.util.List;

public interface PieceDao {
    List<PieceEntity> findByChessGameId(final Long chessGameId);

    Long save(final PieceEntity pieceEntity, final Long chessGameId);
}
