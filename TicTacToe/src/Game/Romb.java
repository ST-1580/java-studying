package Game;

public interface Romb {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move, final int p);
}
