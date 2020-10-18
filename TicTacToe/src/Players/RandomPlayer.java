package Players;

import java.util.Random;

import Game.Cell;
import Game.Move;
import Game.Player;
import Game.Position;

public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Position position, final Cell cell, final int n, final int m, int type) {
        while (true) {
            int r = random.nextInt(n);
            int c = random.nextInt(m);
            if (type == 2) {
                if (n % 2 == 1) {
                    c += Math.abs(r - n / 2);
                } else {
                    if (c < n / 2) {
                        c += Math.abs(r - n / 2 + 1);
                    } else {
                        c += Math.abs(r - n / 2);
                    }
                }
            }
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
