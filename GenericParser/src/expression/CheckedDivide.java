package expression;

import expression.exceptions.DivisionByZeroExceptions;
import expression.exceptions.OverflowExceptions;
import expression.types.*;

import java.math.BigInteger;

public class CheckedDivide<T> extends BinaryOperator<T>{
    public CheckedDivide (TripleExpression<T> first, TripleExpression<T> second, ExpressionType<T> type) {
        super(first, second, type);
    }

    @Override
    protected T result(T first, T second) throws ArithmeticException {
        return type.divide(first, second);
    }
}
