package chess.dao.chess;

import chess.entity.PieceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PieceDaoImplTest {
    @Test
    @DisplayName("체스 게임 아이디를 기준으로 체스말 리스트의 정보를 조회한다.")
    void findByChessGameId() {
        // given
        final PieceDao pieceDao = new MockPieceDao();

        // when
        final List<PieceEntity> pieceEntities = pieceDao.findByChessGameId(1L);

        // then
        assertThat(pieceEntities)
                .isEqualTo(PieceEntityHelper.createPieceEntities());

        assertThat(pieceEntities.size())
                .isSameAs(18);
    }

    @Test
    @DisplayName("체스 게임에 해당하는 체스말의 정보를 저장한다")
    void save() {
        // given
        final PieceDao pieceDao = new MockPieceDao();

        // when
        final Long savedPieceId = pieceDao.save(new PieceEntity(1, 2,
                "PAWN", "WHITE"), 1L);

        // then
        assertThat(savedPieceId)
                .isEqualTo(1L);
    }
}
