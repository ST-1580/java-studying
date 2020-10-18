package expression;

public class Subtract extends AbstractMath {

    public Subtract (Math first, Math second) {
        super(first, second, (x, y) -> x - y,  (x, y) -> x - y, " - ", 1, 4);
    }

    public int getPriority() {
        return 2;
    }
}
