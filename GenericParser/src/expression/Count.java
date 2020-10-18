package expression;

import expression.exceptions.CalculateExceptions;
import expression.types.*;

import java.math.BigInteger;

public class Count<T> extends UnaryOperator<T> {
    public Count (TripleExpression<T> body, ExpressionType<T> type) {
        super(body, type);
    }

    @Override
    protected T result(T body) throws ArithmeticException {
        return type.count(body);
    }
}
