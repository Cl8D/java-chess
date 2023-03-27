package chess.service;

import chess.dao.chess.MockChessGameDao;
import chess.dao.chess.MockPieceDao;
import chess.dao.chess.PieceEntityHelper;
import chess.domain.board.ChessBoard;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.entity.ChessGameEntity;
import chess.entity.PieceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessGameServiceTest {

    @Test
    @DisplayName("사용자의 아이디에 해당하는 체스 게임이 존재하지 않으면 새 게임을 반환한다.")
    void getChessGame_empty() {
        // given
        final MockChessGameDao chessGameDao = new MockChessGameDao();
        final ChessGameService chessGameService = new ChessGameService(
                chessGameDao, new ChessBoardService(new MockPieceDao()));
        final ChessGame expected = new ChessGame(CampType.WHITE);

        // when
        final ChessGame actual = chessGameService.getChessGame(2L);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자의 아이디에 해당하는 체스 게임이 존재하면 해당 게임을 반환한다.")
    void getChessGame() {
        // given
        final Long userId = 1L;
        final ChessGameService chessGameService = getChessGameService(userId);
        final ChessBoard mockProgressBoard = ChessBoardHelper.createMockProgressBoard();
        final ChessGame expected = new ChessGame(mockProgressBoard, CampType.WHITE);

        // when
        final ChessGame actual = chessGameService.getChessGame(userId);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자의 아이디를 기준으로 체스 게임을 조회한다")
    void getChessGameId() {
        // given
        final Long userId = 1L;
        final ChessGameService chessGameService = getChessGameService(userId);

        // when
        Long chessGameId = chessGameService.getChessGameId(userId);

        // then
        assertThat(chessGameId)
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("체스말 정보를 바탕으로 체스말을 저장한다")
    void savePiece() {
        // given
        final Long userId = 1L;
        final ChessGameService chessGameService = getChessGameService(userId);

        // when
        chessGameService.savePiece(PieceEntity.createWithChessGameId(1L, 1, 1,
                "PAWN", "WHITE"));

        // then
        Long chessGameId = chessGameService.getChessGameId(1L);
        assertThat(chessGameId)
                .isEqualTo(1L);
    }

    private ChessGameService getChessGameService(final Long userId) {
        final MockChessGameDao chessGameDao = new MockChessGameDao();
        Long chessGameId = chessGameDao.save(new ChessGameEntity("WHITE", userId));
        final MockPieceDao pieceDao = getMockPieceDao(chessGameId);
        return new ChessGameService(chessGameDao, new ChessBoardService(pieceDao));
    }

    private MockPieceDao getMockPieceDao(final Long chessGameId) {
        final List<PieceEntity> pieceEntities = PieceEntityHelper.createPieceEntities(chessGameId);
        final MockPieceDao pieceDao = new MockPieceDao();
        pieceEntities.forEach(pieceDao::save);
        return pieceDao;
    }
}
