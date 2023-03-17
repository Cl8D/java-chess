package chess.controller;

import chess.controller.status.Start;
import chess.controller.status.Status;
import chess.domain.chess.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        final ChessGame chessGame = new ChessGame();
        start(chessGame);
    }

    private void start(final ChessGame chessGame) {
        Status gameStatus = new Start(chessGame);
        while (gameStatus.isRun()) {
            gameStatus = play(chessGame, gameStatus);
        }
    }

    private Status play(final ChessGame chessGame, Status gameStatus) {
        try {
            List<String> commands = InputView.getCommand();
            final Command command = Command.findCommand(commands);
            gameStatus = gameStatus.checkCommand(command);
            OutputView.printBoard(chessGame.getChessBoard());
            return gameStatus;
        } catch (IllegalArgumentException e) {
            OutputView.print(e.getMessage());
            return play(chessGame, gameStatus);
        }
    }
}

