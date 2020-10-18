package expression;

public class Divide extends AbstractMath {

    public Divide (Math first, Math second) {
        super(first, second, (x, y) -> x / y, (x, y) -> x / y, " / ",3, 5);
    }

    public int getPriority() {
        return 3;
    }
}
