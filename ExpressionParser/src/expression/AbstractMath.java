package expression;

import java.util.Objects;
import java.util.function.BinaryOperator;

public abstract class AbstractMath implements Math {
    private Math first;
    private Math second;
    private BinaryOperator<Integer> calculateInt;
    private BinaryOperator<Double> calculateDouble;
    private String operator;
    private int firstPriority;
    private int secondPriority;

    public AbstractMath (Math first, Math second, BinaryOperator<Integer> calculateInt,
                         BinaryOperator<Double> calculateDouble, String operator,
                         int firstPriority, int secondPriority) {
        this.first = first;
        this.second = second;
        this.calculateInt = calculateInt;
        this.calculateDouble = calculateDouble;
        this.operator = operator;
        this.firstPriority = firstPriority;
        this.secondPriority = secondPriority;
    }

    @Override
    public int evaluate(int number) {
        return calculateInt.apply(first.evaluate(number), second.evaluate(number));
    }

    @Override
    public double evaluate(double number) {
        return calculateDouble.apply(first.evaluate(number), second.evaluate(number));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculateInt.apply(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractMath that = (AbstractMath) o;
        return first.equals(that.first) &&
                second.equals(that.second) &&
                operator.equals(that.operator);
    }

    @Override
    public int hashCode() {
        return 17 * first.hashCode() + 17 * 17 * second.hashCode() + operator.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder ss = new StringBuilder();
        ss.append("(");
        ss.append(first.toString());
        ss.append(operator);
        ss.append(second.toString());
        ss.append(")");
        return ss.toString();
    }

    public String toMiniString() {
        StringBuilder ss = new StringBuilder();
        if (first.getPriority() < firstPriority) {
            ss.append("(");
            ss.append(first.toMiniString());
            ss.append(")");
        } else {
            ss.append(first.toMiniString());
        }
        ss.append(operator);
        if (second.getPriority() < secondPriority) {
            ss.append("(");
            ss.append(second.toMiniString());
            ss.append(")");
        } else {
            ss.append(second.toMiniString());
        }
        return ss.toString();
    }
}
