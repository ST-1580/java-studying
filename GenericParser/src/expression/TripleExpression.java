package expression;

import expression.exceptions.CalculateExceptions;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T> extends ToMiniString {
    T evaluate(T x, T y, T z) throws CalculateExceptions;
}