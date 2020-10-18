package expression;

import expression.exceptions.OverflowExceptions;

public class Pow implements TripleExpression {
    private TripleExpression first, second;

    public Pow(TripleExpression first, TripleExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int f = first.evaluate(x, y, z);
        int s = second.evaluate(x, y, z);
        if ((f == 0 && s == 0) || s < 0) {
            throw new NullPointerException("error");
        }
        if (s == 0) { return 1; }
        if (f == 0) { return 0; }
        if (f == -1) {
            if (s % 2 == 1) { return -1; }
            else { return 1; }
        }
        int ans = pw(s, f);
        return ans;
    }

    private int pw(int st, int f) {
        if (st == 0) {
            return 1;
        }
        if (st == 1) {
            return f;
        }
        int prev = pw(st / 2, f);
        if (check(prev, prev)) {
            int curr = prev * prev;
            if (st % 2 == 0 ) {
                return curr;
            } else {
                if (check(curr, f)) {
                    return curr * f;
                }
            }
        }
        return 0;
    }

    private boolean check(int ans, int f) {
        int max = Integer.MAX_VALUE;
        if (f < 0) {
            int min = Integer.MIN_VALUE;
            if (!(ans < 0 && max / f <= ans) && !(ans > 0 && min / f >= ans)) {
                throw new OverflowExceptions("overflow in pow");
            }
        } else {
            if (max / f < ans) {
                throw new OverflowExceptions("overflow in pow");
            }
        }
        return true;
    }
}
