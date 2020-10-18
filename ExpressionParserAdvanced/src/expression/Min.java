package expression;

import expression.exceptions.OverflowExceptions;

import java.math.BigInteger;

public class Min implements TripleExpression, ExpressionD, ExpressionBi, ExpressionS, ExpressionU, ExpressionL {
    private TripleExpression first, second;
    private ExpressionD firstD, secondD;
    private ExpressionBi firstBi, secondBi;
    private ExpressionS firstS, secondS;
    private ExpressionL firstL, secondL;
    private ExpressionU firstU, secondU;

    public Min (TripleExpression first, TripleExpression second) {
        this.first = first;
        this.second = second;
    }

    public Min (ExpressionD firstD, ExpressionD secondD) {
        this.firstD = firstD;
        this.secondD = secondD;
    }

    public Min (ExpressionBi firstBi, ExpressionBi secondBi) {
        this.firstBi = firstBi;
        this.secondBi = secondBi;
    }

    public Min (ExpressionS firstS, ExpressionS secondS) {
        this.firstS = firstS;
        this.secondS = secondS;
    }

    public Min (ExpressionL firstL, ExpressionL secondL) {
        this.firstL = firstL;
        this.secondL = secondL;
    }

    public Min (ExpressionU firstU, ExpressionU secondU) {
        this.firstU = firstU;
        this.secondU = secondU;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int A = first.evaluate(x, y, z);
        int B = second.evaluate(x, y, z);
        if (A < B) {
            return A;
        } else {
            return B;
        }
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        double A = firstD.evaluateD(x, y, z);
        double B = secondD.evaluateD(x, y, z);
        if (A < B) {
            return A;
        } else {
            return B;
        }
    }

    @Override
    public BigInteger evaluateBi(BigInteger x, BigInteger y, BigInteger z) {
        BigInteger A = firstBi.evaluateBi(x, y, z);
        BigInteger B = secondBi.evaluateBi(x, y, z);
        if (A.compareTo(B) < 0) {
            return A;
        } else {
            return B;
        }
    }

    @Override
    public short evaluateS(short x, short y, short z) {
        short A = firstS.evaluateS(x, y, z);
        short B = secondS.evaluateS(x, y, z);
        if (A < B) {
            return A;
        } else {
            return B;
        }
    }

    @Override
    public long evaluateL(long x, long y, long z) {
        long A = firstL.evaluateL(x, y, z);
        long B = secondL.evaluateL(x, y, z);
        if (A < B) {
            return A;
        } else {
            return B;
        }
    }

    @Override
    public int evaluateU(int x, int y, int z) {
        int A = firstU.evaluateU(x, y, z);
        int B = secondU.evaluateU(x, y, z);
        if (A < B) {
            return A;
        } else {
            return B;
        }
    }
}
