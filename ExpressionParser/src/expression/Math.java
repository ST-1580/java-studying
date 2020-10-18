package expression;

import java.util.Objects;

public interface Math extends Expression, DoubleExpression, TripleExpression {
    String toString();
    boolean equals(Object math);
    String toMiniString();
    int getPriority();
}