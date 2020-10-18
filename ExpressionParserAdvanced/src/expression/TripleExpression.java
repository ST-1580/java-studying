package expression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression extends ToMiniString, ExpressionMode {
    int evaluate(int x, int y, int z);
}