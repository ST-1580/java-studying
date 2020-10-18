package expression;

import expression.exceptions.OverflowExceptions;

import java.math.BigInteger;

public class CheckedMinus implements TripleExpression, ExpressionD, ExpressionBi, ExpressionS, ExpressionU, ExpressionL {
    private TripleExpression body;
    private ExpressionD bodyD;
    private ExpressionBi bodyBi;
    private ExpressionS bodyS;
    private ExpressionL bodyL;
    private ExpressionU bodyU;

    public CheckedMinus (TripleExpression body) {
        this.body = body;
    }

    public CheckedMinus (ExpressionD bodyD) {
        this.bodyD = bodyD;
    }

    public CheckedMinus (ExpressionBi bodyBi) {
        this.bodyBi = bodyBi;
    }

    public CheckedMinus (ExpressionS bodyS) {
        this.bodyS = bodyS;
    }

    public CheckedMinus (ExpressionL bodyL) {
        this.bodyL = bodyL;
    }

    public CheckedMinus (ExpressionU bodyU) {
        this.bodyU = bodyU;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int X = body.evaluate(x, y, z);
        int min = Integer.MIN_VALUE;
        if (X == min) {
            throw new OverflowExceptions("overflow in unar -");
        }
        return -1 * X;
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        double X = bodyD.evaluateD(x, y, z);
        return -1 * X;
    }

    @Override
    public BigInteger evaluateBi(BigInteger x, BigInteger y, BigInteger z) {
        BigInteger X = bodyBi.evaluateBi(x, y, z);
        return X.multiply(new BigInteger("-1"));
    }

    @Override
    public short evaluateS(short x, short y, short z) {
        short X = bodyS.evaluateS(x, y, z);
        return (short) (-1 * X);
    }

    @Override
    public long evaluateL(long x, long y, long z) {
        long X = bodyL.evaluateL(x, y, z);
        return -1 * X;
    }

    @Override
    public int evaluateU(int x, int y, int z) {
        int X = bodyU.evaluateU(x, y, z);
        return -1 * X;
    }
}
