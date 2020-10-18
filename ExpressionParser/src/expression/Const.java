package expression;

import java.util.Objects;

public class Const implements NotAMath {
    int number;
    double number1;
    boolean used = false;

    public Const (int number) {
        this.number = number;
        this.number1 = number;
        used = true;
    }

    public Const (double number1) {
        this.number1 = number1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const that = (Const) o;
        return number1 == that.number1;
    }

    @Override
    public int hashCode() {
        return (int) number1;
    }

    @Override
    public int evaluate(int x) {
        return number;
    }

    @Override
    public double evaluate(double x) {
        return number1;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return number;
    }

    @Override
    public String toString() {
        if (used) {
            return Integer.toString(number);
        } else {
            return Double.toString(number1);
        }
    }

    public String toMiniString() {
        if (used) {
           return Integer.toString(number);
        } else {
            return Double.toString(number1);
        }

    }

    public int getPriority() {
        return 228;
    }
}
