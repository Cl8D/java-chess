package chess.controller.status;

import chess.controller.Command;
import chess.domain.chess.ChessGame;

import java.util.Optional;

public interface Controller {
    Controller checkCommand(final Command command, final long userId);

    boolean isRun();

    Optional<ChessGame> findGame(final long userId);
}
