package expression.types;

public interface ExpressionType<T> {
    T add(T first, T second);
    T subtract(T first, T second);
    T divide(T first, T second);
    T multiply(T first, T second);
    T min(T first, T second);
    T max(T first, T second);
    T count(T body);
    T negative(T body);
    T parse(String s);
}
