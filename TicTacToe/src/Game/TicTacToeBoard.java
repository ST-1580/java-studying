package Game;


public class TicTacToeBoard implements Position{
    private ServerTicTacToeBoard server;

    public TicTacToeBoard(ServerTicTacToeBoard  server) {
        this.server = server;
    }
    @Override
    public boolean isValid(Move move) {
        return server.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return server.getCell(r, c);
    }

    @Override
    public String toString() {
        return server.toString();
    }
}
