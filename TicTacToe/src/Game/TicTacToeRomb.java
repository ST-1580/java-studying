package Game;


public class TicTacToeRomb implements Position {
    private ServerTicTacToeRomb server;

    public TicTacToeRomb(ServerTicTacToeRomb  server) {
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
