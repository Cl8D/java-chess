package chess.domain.board;

import chess.domain.camp.CampType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성하고, 체스판 말의 수가 32개인지 확인한다.")
    void create() {
        // given
        final ChessBoard chessBoard = new ChessBoard();

        // when
        final Map<Position, Piece> board = chessBoard.getBoard();

        // then
        assertThat(board.size())
                .isEqualTo(32);
    }

    @ParameterizedTest(name = "체스판의 해당 위치에 말이 존재하는지 확인한다.")
    @CsvSource(value = {"0:0:true", "3:5:false"}, delimiter = ':')
    void contains(final int rank, final int file, final boolean expected) {
        // given
        final ChessBoard chessBoard = new ChessBoard();

        // when, then
        assertThat(chessBoard.contains(new Position(rank, file)))
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "특정 위치에 존재하는 체스말을 반환한다.")
    @CsvSource(value = {"0:0:ROOK:WHITE", "6:5:PAWN:BLACK"}, delimiter = ':')
    void checkPieceSuccess(final int rank, final int file, final PieceType pieceType, final CampType campType) {
        // given
        final ChessBoard chessBoard = new ChessBoard();

        // when, then
        assertThat(chessBoard.checkPiece(new Position(rank, file)))
                .isEqualTo(new Piece(pieceType, campType));
    }

    @Test
    @DisplayName("없는 위치를 받으면, 예외가 발생한다.")
    void checkPieceFail() {
        // given
        final ChessBoard chessBoard = new ChessBoard();

        // when, then
        assertThatThrownBy(() -> chessBoard.checkPiece(new Position(5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스말이 존재하는 위치를 입력해 주세요.");
    }

    @Test
    @DisplayName("입력받은 위치에 존재하는 체스말을 제거한다.")
    void removePiece() {
        // given
        final ChessBoard chessBoard = new ChessBoard();
        final Position removePosition = new Position(1, 0);

        // when
        chessBoard.removePiece(removePosition);

        // then
        assertThat(chessBoard.contains(removePosition))
                .isFalse();
    }

    @Test
    @DisplayName("입력받은 위치에 체스말을 둔다.")
    void putPiece() {
        // given
        final ChessBoard chessBoard = new ChessBoard();
        final Position putPosition = new Position(2, 0);
        final Piece piece = new Piece(PieceType.PAWN, CampType.WHITE);

        // when
        chessBoard.putPiece(putPosition, piece);

        // then
        assertThat(chessBoard.contains(putPosition))
                .isTrue();
    }
}
