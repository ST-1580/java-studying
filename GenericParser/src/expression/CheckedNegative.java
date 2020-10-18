package expression;

import expression.exceptions.CalculateExceptions;
import expression.types.*;

import java.math.BigInteger;

public class CheckedNegative<T> extends UnaryOperator<T> {
    public CheckedNegative(TripleExpression<T> body, ExpressionType<T> type) {
        super(body, type);
    }

    @Override
    protected T result(T body) throws ArithmeticException {
        return type.negative(body);
    }
}
