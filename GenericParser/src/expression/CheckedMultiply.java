package expression;

import expression.exceptions.OverflowExceptions;
import expression.types.*;

import java.math.BigInteger;

public class CheckedMultiply<T> extends BinaryOperator<T> {
    public CheckedMultiply (TripleExpression<T> first, TripleExpression<T> second, ExpressionType<T> type) {
        super(first, second, type);
    }

    @Override
    protected T result(T first, T second) throws ArithmeticException {
        return type.multiply(first, second);
    }
}
