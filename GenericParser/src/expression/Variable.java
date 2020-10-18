package expression;

import expression.exceptions.CalculateExceptions;
import expression.types.*;

import java.math.BigInteger;

public class Variable<T> implements TripleExpression<T>{
    private String s;

    public Variable(String s) {
        this.s = s;
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalculateExceptions {
        if (s.equals("x")) {
            return x;
        }
        if (s.equals("y")) {
            return y;
        }
        return z;
    }
}

