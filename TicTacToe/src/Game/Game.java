package Game;

public class Game {
    private final boolean log;
    private  Player player1, player2, player3 = null, player4 = null;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Game(final boolean log, final Player player1, final Player player2, final Player player3) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
    }

    public Game(final boolean log, final Player player1, final Player player2, final Player player3, final Player player4) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
    }

    public int play(Board board, int n, int m, int k) {
        int j = 0;
        while (true) {
            Player player;
            for (int i = 1; i <= k; i++) {
                if (i == 1) {
                    player = player1;
                } else if (i == 2) {
                    player = player2;
                } else if (i == 3) {
                    player = player3;
                } else {
                    player = player4;
                }
                final int result = move(board, player, i, n, m,k);
                if (result != -1) {
                    return result;
                }
                j++;
                if (j == m * n) {   
                    return 0;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no, final int n, final int m, final int numOfPlayers) {
        final Move move = player.move(board.getPosition(), board.getCell(), n, m, 1);
        final Result result = board.makeMove(move, numOfPlayers);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
