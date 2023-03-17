package chess.domain.move.piece;

import chess.domain.move.Direction;
import chess.domain.move.Location;
import chess.domain.piece.Position;

import java.util.List;

import static chess.domain.move.piece.Move.PAWN_MAX_MOVE_COUNT;

public class PawnMove implements Movable {
    private static final int ATTACK_INDEX = 1;

    @Override
    public boolean canMove(final Position source, final Position target) {
        if (target.isRankGreaterThan(source)) {
            final Location allPositions = Move.getAllPositions(source,
                    List.of(Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT), PAWN_MAX_MOVE_COUNT);
            return allPositions.contains(target);
        }
        final Location allPositions = Move.getAllPositions(source,
                List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT), PAWN_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return Math.abs(target.calculateRankGap(source)) == ATTACK_INDEX
                && Math.abs(target.calculateFileGap(source)) == ATTACK_INDEX;
    }

    @Override
    public boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible) {
        return canMove(source, target);
    }
}
