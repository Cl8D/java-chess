package chess.dao.chess;

import chess.entity.PieceEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MockPieceDao implements PieceDao {
    private final Map<Long, PieceEntity> STORAGE = new ConcurrentHashMap<>();
    private final AtomicLong pk = new AtomicLong(0L);

    @Override
    public List<PieceEntity> findByChessGameId(final long chessGameId) {
        return STORAGE.values().stream()
                .filter(pieceEntity -> pieceEntity.getChessGameId().equals(chessGameId))
                .map(pieceEntity -> PieceEntity.create(
                        pieceEntity.getId(),
                        pieceEntity.getChessGameId(), pieceEntity.getRank(),
                        pieceEntity.getFile(), pieceEntity.getPieceType(), pieceEntity.getCampType()))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Long insert(final PieceEntity pieceEntity) {
        STORAGE.put(pk.addAndGet(1L), pieceEntity);
        return pk.longValue();
    }

    @Override
    public void deleteByPositions(final long chessGameId, final PieceEntity... pieceEntity) {
        delete(pieceEntity[0]);
        if (pieceEntity.length == 2) {
            delete(pieceEntity[1]);
        }
    }

    @Override
    public void deleteByChessGameId(final long chessGameId) {
        STORAGE.keySet().stream()
                .filter(key -> Objects.equals(STORAGE.get(key).getChessGameId(), chessGameId))
                .forEach(STORAGE::remove);
    }

    private void delete(final PieceEntity pieceEntity) {
        STORAGE.entrySet().stream()
                .filter(entry -> isSamePosition(entry.getValue(), pieceEntity))
                .forEach(entry -> STORAGE.remove(entry.getKey()));
    }

    private boolean isSamePosition(final PieceEntity value, final PieceEntity other) {
        return Objects.equals(value.getRank(), other.getRank())
                && Objects.equals(value.getFile(), other.getFile());
    }
}
