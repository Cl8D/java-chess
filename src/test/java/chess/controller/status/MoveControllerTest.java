package chess.controller.status;

import chess.controller.Command;
import chess.controller.CommandType;
import chess.service.ChessGameService;
import chess.service.MockServiceManagerHandler;
import chess.service.ServiceHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoveControllerTest {

    private ChessGameService chessGameService;

    @BeforeEach
    void init() {
        final ServiceHandler mockServiceManagerHandler = new MockServiceManagerHandler();
        chessGameService = mockServiceManagerHandler.chessGameService();
    }

    @Test
    @DisplayName(value = "게임이 움직임 상태일 때 start를 입력하면 예외가 발생한다.")
    void checkCommandStart() {
        // given
        long userId = 1L;
        final MoveController moveController = new MoveController(chessGameService);
        final Command command = new Command(CommandType.START, List.of("start"));

        // when, then
        assertThatThrownBy(() -> moveController.checkCommand(command, userId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 시작이 완료되었습니다.");
    }

    @Test
    @DisplayName(value = "게임이 움직임 상태일 때 end를 입력하면 게임이 종료된다.")
    void checkCommandEnd() {
        // given
        long userId = 1L;
        final MoveController moveController = new MoveController(chessGameService);
        final Command command = new Command(CommandType.END, List.of("end"));

        // when
        Controller controller = moveController.checkCommand(command, userId);

        // then
        assertThat(controller)
                .isInstanceOf(EndController.class);
    }

    @Test
    @DisplayName(value = "게임이 움직임 상태일 때 status를 입력하면 게임 결과를 출력하도록 제어한다.")
    void checkCommandStatus() {
        // given
        long userId = 1L;
        final MoveController moveController = new MoveController(chessGameService);
        final Command command = new Command(CommandType.STATUS, List.of("status"));

        // when
        Controller controller = moveController.checkCommand(command, userId);

        // then
        assertThat(controller)
                .isInstanceOf(StatusController.class);
    }


    @ParameterizedTest(name = "게임이 움직임 상태일 때 올바르지 않은 명령어 형식을 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"move", "move a2", "", " move ", "move a2 a3 a5", "move a2a3"})
    void checkCommandValidate(final String commands) {
        // given
        long userId = 1L;
        final MoveController moveController = new MoveController(chessGameService);
        final Command command = new Command(CommandType.MOVE, Arrays.asList(commands.split(" ")));

        // when, then
        assertThatThrownBy(() -> moveController.checkCommand(command, userId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'move source위치 target위치 - 예. move b2 b3'와 같은 형태로 입력해 주세요.");
    }

    @Test
    @DisplayName(value = "게임이 움직임 상태일 때 실행 중인지 체크하면 true 반환한다")
    void isRun() {
        // given
        final MoveController moveController = new MoveController(chessGameService);

        // when
        boolean isRun = moveController.isRun();

        // then
        assertThat(isRun)
                .isTrue();
    }
}
