package expression;

import expression.exceptions.OverflowExceptions;

import java.math.BigInteger;

public class Count implements TripleExpression, ExpressionD, ExpressionBi, ExpressionS, ExpressionU, ExpressionL {
    private TripleExpression body;
    private ExpressionD bodyD;
    private ExpressionBi bodyBi;
    private ExpressionS bodyS;
    private ExpressionL bodyL;
    private ExpressionU bodyU;

    public Count (TripleExpression body) {
        this.body = body;
    }

    public Count (ExpressionD bodyD) {
        this.bodyD = bodyD;
    }

    public Count (ExpressionBi bodyBi) {
        this.bodyBi = bodyBi;
    }

    public Count (ExpressionS bodyS) {
        this.bodyS = bodyS;
    }

    public Count (ExpressionL bodyL) {
        this.bodyL = bodyL;
    }

    public Count (ExpressionU bodyU) {
        this.bodyU = bodyU;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int X = body.evaluate(x, y, z);
        return Integer.bitCount(X);
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        double X = bodyD.evaluateD(x, y, z);
        return (double) Long.bitCount(Double.doubleToLongBits(X));
    }

    @Override
    public BigInteger evaluateBi(BigInteger x, BigInteger y, BigInteger z) {
        BigInteger X = bodyBi.evaluateBi(x, y, z);
        return new BigInteger(Integer.toString(X.bitCount()));
    }

    @Override
    public short evaluateS(short x, short y, short z) {
        short X = bodyS.evaluateS(x, y, z);
        return (short) Integer.bitCount(X & 0xffff);
    }

    @Override
    public long evaluateL(long x, long y, long z) {
        long X = bodyL.evaluateL(x, y, z);
        return Long.bitCount(X);
    }

    @Override
    public int evaluateU(int x, int y, int z) {
        int X = bodyU.evaluateU(x, y, z);
        return Integer.bitCount(X);
    }
}
