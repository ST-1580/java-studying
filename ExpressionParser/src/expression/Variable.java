package expression;

import java.util.Objects;

public class Variable implements NotAMath {
    String s;

    public Variable (String s) {
        this.s = s;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (s.equals("x")) {
            return x;
        } else if (s.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable that = (Variable) o;
        return Objects.equals(s, that.s);
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }

    @Override
    public String toString() {
        return s;
    }

    @Override
    public String toMiniString() {
        return s;
    }

    public int getPriority() {
        return 322;
    }
}

