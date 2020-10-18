package expression;

import java.math.BigInteger;

public class Variable implements TripleExpression, ExpressionD, ExpressionBi, ExpressionS, ExpressionU, ExpressionL {
    String s;

    public Variable (String s) {
        this.s = s;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (s.equals("x")) {
            return x;
        } else if (s.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        if (s.equals("x")) {
            return x;
        } else if (s.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public BigInteger evaluateBi(BigInteger x, BigInteger y, BigInteger z) {
        if (s.equals("x")) {
            return x;
        } else if (s.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public short evaluateS(short x, short y, short z) {
        if (s.equals("x")) {
            return x;
        } else if (s.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public long evaluateL(long x, long y, long z) {
        if (s.equals("x")) {
            return x;
        } else if (s.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public int evaluateU(int x, int y, int z) {
        if (s.equals("x")) {
            return x;
        } else if (s.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}

