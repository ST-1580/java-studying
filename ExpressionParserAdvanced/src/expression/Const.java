package expression;

import java.math.BigInteger;

public class Const implements TripleExpression, ExpressionD, ExpressionBi, ExpressionS, ExpressionU, ExpressionL {
    private int number, numberU;
    private double numberD;
    private BigInteger numberBi;
    private short numberS;
    private long numberL;

    public Const (int number) {
            this.number = number;
            this.numberU = number;
    }

    public Const (double numberD) {
        this.numberD = numberD;
    }

    public Const (BigInteger numberBi) {
        this.numberBi = numberBi;
    }

    public Const (short numberS) {
        this.numberS = numberS;
    }

    public Const (long numberL) {
        this.numberL = numberL;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return number;
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        return numberD;
    }

    @Override
    public BigInteger evaluateBi(BigInteger x, BigInteger y, BigInteger z) {
        return numberBi;
    }

    @Override
    public short evaluateS(short x, short y, short z) {
        return numberS;
    }

    @Override
    public long evaluateL(long x, long y, long z) {
        return numberL;
    }

    @Override
    public int evaluateU(int x, int y, int z) {
        return numberU;
    }
}
