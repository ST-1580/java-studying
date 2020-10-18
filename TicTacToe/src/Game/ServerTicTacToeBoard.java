package Game;


import java.lang.annotation.Retention;
import java.util.Arrays;
import java.util.Map;

public class ServerTicTacToeBoard implements Board {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.P, 'P',
            Cell.T, 'T'
    );

    private final Cell[][] cells;
    private Cell turn;
    private int m, n, k;

    public ServerTicTacToeBoard(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return new TicTacToeBoard(this);
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
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int i = 0; i < m; i++) {
            sb.append(i);
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
