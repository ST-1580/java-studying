package expression;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int ans = new Add(
                new Subtract(
                        new Multiply(
                                new Variable("x"), new Variable("x")),
                        new Multiply(
                                new Const(2), new Variable("x"))
                ),
                new Const(1)).evaluate(x);
        double d = new Add(
                new Subtract(
                        new Multiply(
                                new Variable("x"), new Variable("x")),
                        new Multiply(
                                new Const(2), new Variable("x"))
                ),
                new Const(1)).evaluate(5.5);
        String s = new Add (new Subtract (new Multiply (new Const (18), new Variable ("x")),
                new Subtract (new Const (134), new Subtract (new Const (32),
                        new Multiply (new Const (8), new Variable ("x"))))), new Variable ("x")).toMiniString();
        String sMini = new Add(
                new Subtract(
                        new Multiply(
                                new Add(new Const(2), new Const(5)), new Variable("x")),
                        new Multiply(
                                new Const(2), new Variable("x"))
                ),
                new Const(1)).toMiniString();
        String kek = new Add(new Const(10.2), new Const(11.8)).toString();
        System.out.println(ans);
        System.out.println(s);
        System.out.println(kek);
        // System.out.println(isEqualsFirst);
        // System.out.println(isEqualsSecond);
    }
}
