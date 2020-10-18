package expression;

import expression.exceptions.VariableExceptions;

public class Log2 implements TripleExpression {
    private TripleExpression body;

    public Log2(TripleExpression body) {
        this.body = body;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int ans = body.evaluate(x, y, z);
        if (ans <= 0) {
            throw new VariableExceptions("error Log2");
        }
        if (ans == 1) {
            return 0;
        }
        int cnt = 0;
        while (ans >= 2) {
            ans /= 2;
            cnt++;
        }
        return cnt;
    }
}
