package chess.controller.status;

import chess.controller.Command;
import chess.domain.chess.ChessGame;
import chess.service.ChessGameService;

import java.util.Optional;

public final class StartController implements Controller {
    private final ChessGameService chessGameService;

    public StartController(final ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    @Override
    public Controller checkCommand(final Command command, final long userId) {
        if (command.isEnd()) {
            return new EndController();
        }
        if (!command.isStart()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
        return new MoveController(chessGameService);
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
}
