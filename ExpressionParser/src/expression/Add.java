package expression;

import java.awt.event.ActionEvent;

public class Add extends AbstractMath {

    public Add (Math first, Math second) {
        super(first, second, (x, y) -> x + y, (x, y) -> x + y, " + ", 1, 1);
    }

    public int getPriority() {
        return 1;
    }
}
