package Players;

import Game.*;

public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell, final int n, final int m, int type) {
        // Board board = (Board) position;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
