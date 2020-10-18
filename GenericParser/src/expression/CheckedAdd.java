package expression;

import expression.types.ExpressionType;

public class CheckedAdd<T> extends BinaryOperator<T> {
    public CheckedAdd (TripleExpression<T> first, TripleExpression<T> second, ExpressionType<T> type) {
        super(first, second, type);
    }

    @Override
    protected T result(T first, T second) throws ArithmeticException {
        return type.add(first, second);
    }
}
