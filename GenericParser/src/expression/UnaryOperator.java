package expression;

import expression.exceptions.CalculateExceptions;
import expression.types.ExpressionType;

public abstract class UnaryOperator<T> implements TripleExpression<T> {
    private TripleExpression<T> body;
    public ExpressionType<T> type;

    public UnaryOperator (TripleExpression<T> body, ExpressionType<T> type) {
        this.body = body;
        this.type = type;
    }

    protected abstract T result(T body) throws ArithmeticException;

    @Override
    public T evaluate(T x, T y, T z) throws CalculateExceptions {
        return result(body.evaluate(x, y, z));
    }
}
