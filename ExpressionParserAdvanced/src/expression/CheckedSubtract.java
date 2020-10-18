package expression;

import expression.exceptions.OverflowExceptions;

import java.math.BigInteger;

public class CheckedSubtract implements TripleExpression, ExpressionD, ExpressionBi, ExpressionS, ExpressionU, ExpressionL {
    private TripleExpression first, second;
    private ExpressionD firstD, secondD;
    private ExpressionBi firstBi, secondBi;
    private ExpressionS firstS, secondS;
    private ExpressionL firstL, secondL;
    private ExpressionU firstU, secondU;

    public CheckedSubtract (TripleExpression first, TripleExpression second) {
        this.first = first;
        this.second = second;
    }

    public CheckedSubtract (ExpressionD firstD, ExpressionD secondD) {
        this.firstD = firstD;
        this.secondD = secondD;
    }

    public CheckedSubtract (ExpressionBi firstBi, ExpressionBi secondBi) {
        this.firstBi = firstBi;
        this.secondBi = secondBi;
    }

    public CheckedSubtract (ExpressionS firstS, ExpressionS secondS) {
        this.firstS = firstS;
        this.secondS = secondS;
    }

    public CheckedSubtract (ExpressionL firstL, ExpressionL secondL) {
        this.firstL = firstL;
        this.secondL = secondL;
    }

    public CheckedSubtract (ExpressionU firstU, ExpressionU secondU) {
        this.firstU = firstU;
        this.secondU = secondU;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int A = first.evaluate(x, y, z);
        int B = second.evaluate(x, y, z);
        if (A >= 0) {
            int max = Integer.MAX_VALUE;
            if (A - max <= B) {
                return A - B;
            }
        } else {
            if (B >= 0) {
                int min = Integer.MIN_VALUE;
                if (A >= min + B) {
                    return A - B;
                }
            } else {
                return A - B;
            }
        }
        throw new OverflowExceptions("overflow in -");
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        double A = firstD.evaluateD(x, y, z);
        double B = secondD.evaluateD(x, y, z);
        return A - B;
    }

    @Override
    public BigInteger evaluateBi(BigInteger x, BigInteger y, BigInteger z) {
        BigInteger A = firstBi.evaluateBi(x, y, z);
        BigInteger B = secondBi.evaluateBi(x, y, z);
        return A.subtract(B);
    }

    @Override
    public short evaluateS(short x, short y, short z) {
        short A = firstS.evaluateS(x, y, z);
        short B = secondS.evaluateS(x, y, z);
        return (short) (A - B);
    }

    @Override
    public long evaluateL(long x, long y, long z) {
        long A = firstL.evaluateL(x, y, z);
        long B = secondL.evaluateL(x, y, z);
        return A - B;
    }

    @Override
    public int evaluateU(int x, int y, int z) {
        int A = firstU.evaluateU(x, y, z);
        int B = secondU.evaluateU(x, y, z);
        return A - B;
    }
}
