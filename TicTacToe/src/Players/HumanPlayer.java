package Players;

import java.io.PrintStream;
import java.util.Scanner;

import Game.Cell;
import Game.Move;
import Game.Player;
import Game.Position;


public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell, final int n, final int m, int type) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            if (type == 1) {
                out.println("Enter row and column");
            } else {
                out.println("Enter row and column. The first element of the row has number of column 0");
            }
            int currRow = 0, currColumn = 0;
            if (in.hasNextInt()) {
                currRow = in.nextInt();
                if (in.hasNextInt()) {
                    currColumn = in.nextInt();
                } else {
                    String s = in.nextLine();
                    System.out.println("Incorrect input, try again");
                    continue;
                }
            } else {
                String s = in.nextLine();
                System.out.println  ("Incorrect input, try again");
                continue;
            }
            if (type == 2) {
                if (n % 2 == 1) {
                    currColumn += Math.abs(currRow - n / 2);
                } else {
                    if (currRow < n / 2) {
                        currColumn += Math.abs(currRow - n / 2 + 1);
                    } else {
                        currColumn += Math.abs(currRow - n / 2);
                    }
                }
            }
            final Move move = new Move(currRow, currColumn, cell);
            if (position.isValid(move)) {
                return move;
            }
            final int row = move.getRow();
            final int column = move.getColumn();
            out.println("Move " + move + " is invalid");
        }
    }
}
