package expression.types;

import java.math.BigInteger;

public class BigIntegerType implements ExpressionType<BigInteger> {
    @Override
    public BigInteger add(BigInteger first, BigInteger second) {
        return first.add(second);
    }

    @Override
    public BigInteger subtract(BigInteger first, BigInteger second) {
        return first.subtract(second);
    }

    @Override
    public BigInteger multiply(BigInteger first, BigInteger second) {
        return first.multiply(second);
    }

    @Override
    public BigInteger divide(BigInteger first, BigInteger second) {
        return first.divide(second);
    }

    @Override
    public BigInteger negative(BigInteger body) {
        return body.negate();
    }

    @Override
    public BigInteger count(BigInteger body) {
        return new BigInteger(Integer.toString(body.bitCount()));
    }

    @Override
    public BigInteger min(BigInteger first, BigInteger second) {
        return first.min(second);
    }

    @Override
    public BigInteger max(BigInteger first, BigInteger second) {
        return first.max(second);
    }

    @Override
    public BigInteger parse(String s) {
        return new BigInteger(s);
    }
}
