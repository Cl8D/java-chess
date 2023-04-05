package chess.controller;

import chess.controller.mapper.ChessBoardDtoMapper;
import chess.controller.status.Controller;
import chess.controller.status.StartController;
import chess.domain.chess.ChessGame;
import chess.service.ChessGameService;
import chess.service.ServiceManager;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public final class ChessHandler {

    private final UserService userService;
    private final ChessGameService chessGameService;

    public ChessHandler(final ServiceManager serviceManager) {
        this.userService = serviceManager.userService();
        this.chessGameService = serviceManager.chessGameService();
    }

    public void run() {
        final long userId = getUserId();
        OutputView.printStartMessage();
        Controller controller = new StartController(chessGameService);
        while (controller.isRun()) {
            controller = play(controller, userId);
        }
    }

    private Controller play(Controller controller, final long userId) {
        try {
            final List<String> commands = InputView.getCommands();
            final Command command = Command.findCommand(commands);
            controller = controller.checkCommand(command, userId);
            final Optional<ChessGame> chessGame = controller.findGame(userId);
            chessGame.ifPresent(game ->
                    OutputView.printBoard(ChessBoardDtoMapper.createChessBoardDto(game.getChessBoard())));
            return controller;
        } catch (IllegalArgumentException e) {
            OutputView.print(e.getMessage());
            return play(controller, userId);
        }
    }

    private long getUserId() {
        OutputView.printUserNameInputMessage();
        return getUserIdWithRetry(InputView::getCommand);
    }

    private long getUserIdWithRetry(final Supplier<String> supplier) {
        try {
            final String name = supplier.get();
            return userService.getOrCreateUserId(name);
        } catch (IllegalArgumentException e) {
            OutputView.print(e.getMessage());
            return getUserIdWithRetry(supplier);
        }
    }
}

