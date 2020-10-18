package expression.types;

import expression.exceptions.DivisionByZeroExceptions;
import expression.exceptions.OverflowExceptions;


public class IntegerType implements ExpressionType<Integer> {
    boolean checkOverflow;
    private final int max = Integer.MAX_VALUE;
    private final int min = Integer.MIN_VALUE;

    public IntegerType(boolean checkOverflow) {
        this.checkOverflow = checkOverflow;
    }

    @Override
    public Integer add(Integer first, Integer second) {
        if (checkOverflow) {
            if ((second > 0 && Integer.MAX_VALUE - second < first) || (second < 0 && Integer.MIN_VALUE - second > first)) {
                throw new OverflowExceptions("overflow in +");
            }
        }
        return first + second;
    }

    @Override
    public Integer subtract(Integer first, Integer second) {
        if (checkOverflow) {
            if ((second < 0 && max + second < first) || (second > 0 && min + second > first)) {
                throw new OverflowExceptions("overflow in -");
            }
            
        }
        return first - second;
    }

    @Override
    public Integer divide(Integer first, Integer second) {
        if (checkOverflow) {
            if (second == 0) {
                throw new DivisionByZeroExceptions("division by zero");
            }
            if (first == min && second == -1) {
                throw new OverflowExceptions("overflow in /");
            }
        }
        return first / second;
    }

    @Override
    public Integer multiply(Integer first, Integer second) {
        if (checkOverflow) {
            if (first != 0 && second != 0 && ((first * second) / second != first || (first * second) / first != second)) {
                throw new OverflowExceptions("overflow in *");
            }
        }
        return first * second;

    }

    @Override
    public Integer min(Integer first, Integer second) {
        return Integer.min(first, second);
    }

    @Override
    public Integer max(Integer first, Integer second) {
        return Integer.max(first, second);
    }

    @Override
    public Integer count(Integer body) {
        return Integer.bitCount(body);
    }

    @Override
    public Integer negative(Integer body) {
        if (checkOverflow) {
            if (body == min) {
                throw new OverflowExceptions("overflow in unar -");
            }
        }
        return -1 * body;
    }

    @Override
    public Integer parse(String s) {
        return Integer.parseInt(s);
    }
}
