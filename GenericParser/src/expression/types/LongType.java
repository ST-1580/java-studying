package expression.types;

public class LongType implements ExpressionType<Long> {

    @Override
    public Long add(Long first, Long second) {
        return first + second;
    }

    @Override
    public Long subtract(Long first, Long second) {
        return first - second;
    }

    @Override
    public Long divide(Long first, Long second) {
        return first / second;
    }

    @Override
    public Long multiply(Long first, Long second) {
        return first * second;
    }

    @Override
    public Long max(Long first, Long second) {
        return Long.max(first, second);
    }

    @Override
    public Long min(Long first, Long second) {
        return Long.min(first, second);
    }

    @Override
    public Long count(Long body) {
        return (long) Long.bitCount(body);
    }

    @Override
    public Long negative(Long body) {
        return -1 * body;
    }

    @Override
    public Long parse(String s) {
        return Long.parseLong(s);
    }
}
