package expression;

import expression.exceptions.CalculateExceptions;

public class Const<T> implements TripleExpression<T> {
    private T value;

    public Const(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalculateExceptions {
        return value;
    }
}
