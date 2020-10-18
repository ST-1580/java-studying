package expression;

import expression.exceptions.CalculateExceptions;
import expression.types.ExpressionType;

public abstract class BinaryOperator<T> implements TripleExpression<T> {
    private final TripleExpression<T> first, second;
    public final ExpressionType<T> type;

    public BinaryOperator (TripleExpression<T> first, TripleExpression<T> second, ExpressionType<T> type) {
        this.first = first;
        this.second = second;
        this.type = type;
    }

    protected abstract T result(T first, T second) throws ArithmeticException;

    @Override
    public T evaluate(T x, T y, T z) throws CalculateExceptions {
        return result(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}
