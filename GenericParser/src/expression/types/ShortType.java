package expression.types;

public class ShortType implements ExpressionType<Short> {

    @Override
    public Short add(Short first, Short second) {
        return (short) (first + second);
    }

    @Override
    public Short subtract(Short first, Short second) {
        return (short) (first - second);
    }

    @Override
    public Short divide(Short first, Short second) {
        return (short) (first / second);
    }

    @Override
    public Short multiply(Short first, Short second) {
        return (short) (first * second);
    }

    @Override
    public Short min(Short first, Short second) {
        if (first < second) {
            return first;
        } else {
            return second;
        }
    }

    @Override
    public Short max(Short first, Short second) {
        if (first > second) {
            return first;
        } else {
            return second;
        }
    }

    @Override
    public Short count(Short body) {
        return (short) Integer.bitCount(body & 0xffff);
    }

    @Override
    public Short negative(Short body) {
        return (short) (-1 * body);
    }

    @Override
    public Short parse(String s) {
        return (short) Integer.parseInt(s);
    }
}
