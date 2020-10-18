package expression;

import expression.exceptions.VariableExceptions;

public class Log implements TripleExpression {
    private TripleExpression first, second;

    public Log(TripleExpression first, TripleExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int f = first.evaluate(x, y, z);
        int s = second.evaluate(x, y, z);
        if (f <= 0 || s <= 0 || s == 1) {
            throw new VariableExceptions("error Log");
        }
        int ans = s;
        int cnt = 0;
        while (ans <= f) {
            int max = Integer.MAX_VALUE;
            if (max / s >= ans) {
                ans *= s;
            } else {
                cnt++;
                break;
            }
            cnt++;
        }
        return cnt;
    }
}
