package Game;


import java.util.Arrays;
import java.util.Map;

public class ServerTicTacToeRomb implements Romb {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.P, 'P',
            Cell.T, 'T',
            Cell.N, ' '
    );

    private final Cell[][] cells;
    private Cell turn;
    private int m, n, k;

    ServerTicTacToeRomb(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.cells = new Cell[n][m];
        if (n % 2 == 0) {
            int sr = n / 2;
            int z = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cells[i][j] = Cell.N;
                }
                for (int j = sr - z; j < sr + z; j++) {
                    cells[i][j] = Cell.E;
                }
                if (z <= n / 2 && i >= n / 2) {
                    z--;
                } else if (z < n / 2 && i < n / 2) {
                    z++;
                }
            }
        } else {
            int sr = n / 2;
            int z = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cells[i][j] = Cell.N;
                }
                for (int j = sr - z; j <= sr + z; j++) {
                    cells[i][j] = Cell.E;
                }
                if (z < n / 2 && i < n / 2) {
                    z++;
                } else  {
                    z--;
                }
            }
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return new TicTacToeRomb(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move, final int numPl) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        int currRow = move.getRow();
        int currColumn = move.getColumn();

        cells[currRow][currColumn] = move.getValue();

        int curr = 0;
        for (int i = Math.max(0, currRow - k + 1); i < Math.min(n, currRow + k); i++) {
            if (curr == k) {
                return Result.WIN;
            }
            if (cells[i][currColumn] == cells[currRow][currColumn]) {
                curr++;
            } else {
                curr = 0;
            }
        }
        if (curr == k) {
            return Result.WIN;
        }
        curr = 0;
        for (int i = Math.max(0, currColumn - k + 1); i < Math.min(n, currColumn + k); i++) {
            if (curr == k) {
                return Result.WIN;
            }
            if (cells[currRow][i] == cells[currRow][currColumn]) {
                curr++;
            } else {
                curr = 0;
            }
        }
        if (curr == k) {
            return Result.WIN;
        }
        int i = Math.max(0, currRow - k + 1);
        int j = Math.max(0, currColumn - k + 1);
        curr = 0;
        for (int q = 0; q < 2 * k; q++) {
            if (curr == k) {
                return Result.WIN;
            }
            if (i + q >= m || j + q >= n) {
                break;
            }
            if (cells[i + q][j + q] == cells[currRow][currColumn]) {
                curr++;
            } else {
                curr = 0;
            }
        }
        if (curr == k) {
            return Result.WIN;
        }
        i = Math.max(0, currRow - k + 1);
        j = Math.min(n - 1, currColumn + k - 1);
        curr = 0;
        for (int q = 0; q < 2 * k; q++) {
            if (curr == k) {
                return Result.WIN;
            }
            if (i + q >= m || j - q < 0) {
                break;
            }
            if (cells[i + q][j - q] == cells[currRow][currColumn]) {
                curr++;
            } else {
                curr = 0;
            }
        }
        if (curr == k) {
            return Result.WIN;
        }

        if (numPl == 2) {
            if (turn == Cell.X) {
                turn = Cell.O;
            } else if (turn == Cell.O) {
                turn = Cell.X;
            }
        } else if (numPl == 3) {
            if (turn == Cell.X) {
                turn = Cell.O;
            } else if (turn == Cell.O) {
                turn = Cell.T;
            } else if (turn == Cell.T) {
                turn = Cell.X;
            }
        } else if (numPl == 4) {
            if (turn == Cell.X) {
                turn = Cell.O;
            } else if (turn == Cell.O) {
                turn = Cell.T;
            } else if (turn == Cell.T) {
                turn = Cell.P;
            } else {
                turn = Cell.X;
            }
        }
        return Result.UNKNOWN;
    }

    public boolean isValid(final Move move) {
        int currRow = move.getRow();
        int currColumn = 0;
        if (n % 2 == 1) {
            currColumn = Math.abs(currRow - n / 2);
        } else {
            if (currRow < n / 2) {
                currColumn = Math.abs(currRow - n / 2 + 1);
            } else {
                currColumn = Math.abs(currRow - n / 2);
            }
        }
        return 0 <= currRow && currRow < n
                && currColumn <= move.getColumn() && move.getColumn() < n - currColumn
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < n; r++) {
            if (r != 0 ) {
                sb.append("\n");
            }
            sb.append(r);
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
