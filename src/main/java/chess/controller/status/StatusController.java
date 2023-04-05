package chess.controller.status;

import chess.controller.Command;
import chess.controller.dto.ChessResultDto;
import chess.controller.mapper.ChessResultDtoMapper;
import chess.domain.chess.ChessGame;
import chess.domain.chess.ChessGameCalculator;
import chess.domain.chess.vo.ChessScore;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.Optional;

public final class StatusController implements Controller {
    private final ChessGameService chessGameService;

    StatusController(final ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    @Override
    public Controller checkCommand(final Command command, final long userId) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작이 완료되었습니다.");
        }
        if (command.isEnd()) {
            return new EndController();
        }
        if (command.isMove()) {
            return new MoveController(chessGameService).move(command, userId);
        }
        return getStatus(true, userId);
    }

    @Override
    public boolean isRun() {
        return true;
    }

    @Override
    public Optional<ChessGame> findGame(final long userId) {
        final ChessGame chessGame = chessGameService.getOrCreateChessGame(userId);
        return Optional.of(chessGame);
    }

    Controller getStatus(final boolean isGameRun, final long userId) {
        if (!isGameRun) {
            OutputView.print("킹이 사망하여 게임이 종료되었습니다!");
            runCalculator(userId).run();
            chessGameService.clear(userId);
            return new EndController();
        }
        runCalculator(userId).run();
        return this;
    }

    private Runnable runCalculator(final long userId) {
        return () -> {
            final ChessGame chessGame = chessGameService.getOrCreateChessGame(userId);
            final ChessScore chessResult = ChessGameCalculator.calculate(chessGame);
            final ChessResultDto chessResultDto = ChessResultDtoMapper.from(chessResult);
            OutputView.printChessResult(chessResultDto);
        };
    }
}
