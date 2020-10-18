package expression;

import expression.types.*;

import java.math.BigInteger;

public class Max<T> extends BinaryOperator<T>{
    public Max (TripleExpression<T> first, TripleExpression<T> second, ExpressionType<T> type) {
        super(first, second, type);
    }

    @Override
    protected T result(T first, T second) throws ArithmeticException {
        return type.max(first, second);
    }
}
