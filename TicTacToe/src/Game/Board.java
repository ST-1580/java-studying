package Game;

interface Board {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move, final int p);
}
