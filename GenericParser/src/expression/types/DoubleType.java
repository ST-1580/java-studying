package expression.types;

public class DoubleType implements ExpressionType<Double> {
    
    @Override
    public Double add(Double first, Double second) {
        return first + second;
    }

    @Override
    public Double subtract(Double first, Double second) {
        return first - second;
    }

    @Override
    public Double multiply(Double first, Double second) {
        return first * second;
    }

    @Override
    public Double divide(Double first, Double second) {
        return first / second;
    }

    @Override
    public Double negative(Double body) {
        return -1 * body;
    }

    @Override
    public Double count(Double body) {
        return (double) Long.bitCount(Double.doubleToLongBits(body));
    }

    @Override
    public Double min(Double first, Double second) {
        return Double.min(first, second);
    }

    @Override
    public Double max(Double first, Double second) {
        return Double.max(first, second);
    }

    @Override
    public Double parse(String s) {
        return Double.parseDouble(s);
    }
}
