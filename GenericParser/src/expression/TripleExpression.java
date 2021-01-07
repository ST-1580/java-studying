package expression;

import expression.exceptions.CalculateExceptions;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
// :NOTE: where is a bound on generic type? Now I can divide String by String
public interface TripleExpression<T> extends ToMiniString {
    T evaluate(T x, T y, T z) throws CalculateExceptions;
}