package Game;


public interface Player {
    Move move(Position position, Cell cell, int n, int m, int type);
}
