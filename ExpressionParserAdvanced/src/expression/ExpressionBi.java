package expression;

import java.math.BigInteger;

public interface ExpressionBi extends ExpressionMode {
    BigInteger evaluateBi(BigInteger x, BigInteger y, BigInteger z);
}
