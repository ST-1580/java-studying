package expression;

import expression.types.*;

public class Min<T> extends BinaryOperator<T> {
    public Min (TripleExpression<T> first, TripleExpression<T> second, ExpressionType<T> type) {
        super(first, second, type);
    }

    @Override
    protected T result(T first, T second) throws ArithmeticException {
        return type.min(first, second);
    }
}
