package expression;

import expression.exceptions.OverflowExceptions;

public class Pow2 implements TripleExpression {
    private TripleExpression body;

    public Pow2(TripleExpression body) {
        this.body = body;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int s = body.evaluate(x, y, z);
        if (s < 0 || s > 31) {
            throw new OverflowExceptions("overflow in Pow");
        }
        return 1 << s;
    }

}
