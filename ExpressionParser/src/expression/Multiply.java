package expression;

import java.math.BigDecimal;

public class Multiply extends AbstractMath {

    public Multiply (Math first, Math second) {
        super(first, second, (x, y) -> x * y, (x, y) -> x * y, " * ", 3, 4);
    }

    public int getPriority() {
        return 4;
    }
}
