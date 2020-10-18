package expression.generic;


import expression.*;
import expression.exceptions.*;
import expression.parser.CustomString;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

public class GenericTabulator implements Tabulator {
    private char curr;
    private CustomString cs;

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] ans = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    ans[x - x1][y - y1][z - z1] = null;
                    if (mode.equals("i")) {
                        TripleExpression expressionAfterParse = (TripleExpression) parse(expression, "i");
                        try {
                            ans[x - x1][y - y1][z - z1] = expressionAfterParse.evaluate(x, y, z);
                        } catch (CalculateExceptions ignored) {
                        }
                    } else if (mode.equals("d")) {
                        ExpressionD expressionAfterParse = (ExpressionD) parse(expression, "d");
                        ans[x - x1][y - y1][z - z1] = expressionAfterParse.evaluateD((double) x, (double) y, (double) z);
                    } else if (mode.equals("bi")){
                        ExpressionBi expressionAfterParse = (ExpressionBi) parse(expression, "bi");
                        try {
                            ans[x - x1][y - y1][z - z1] = expressionAfterParse.evaluateBi(
                                    new BigInteger(Integer.toString(x)), new BigInteger(Integer.toString(y)), new BigInteger(Integer.toString(z))
                            );
                        } catch (ArithmeticException ignored) {
                        }
                    } else if (mode.equals("s")) {
                        ExpressionS expressionAfterParse = (ExpressionS) parse(expression, "s");
                        try {
                            ans[x - x1][y - y1][z - z1] = expressionAfterParse.evaluateS((short) x, (short) y, (short) z);
                        } catch (ArithmeticException ignored) {
                        }
                    } else if (mode.equals("l")) {
                        ExpressionL expressionAfterParse = (ExpressionL) parse(expression, "l");
                        try {
                            ans[x - x1][y - y1][z - z1] = expressionAfterParse.evaluateL((long) x, (long) y, (long) z);
                        } catch (ArithmeticException ignored) {
                        }
                    } else {
                        ExpressionU expressionAfterParse = (ExpressionU) parse(expression, "u");
                        try {
                            ans[x - x1][y - y1][z - z1] = expressionAfterParse.evaluateU(x, y, z);
                        } catch (ArithmeticException ignored) {
                        }
                    }
                }
            }
        }
        return ans;
    }

    private final Map<Character, Integer> priority = Map.of(
            '<', 2,
            '>', 2,
            '+', 3,
            '-', 3,
            '*', 4,
            '/', 4,
            'c', 5,
            '$', 1,
            '^', 1
    );

    private final Set<Character> validAfterOperationF = Set.of(
            '(', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'c', 'x', 'y', 'z'
    );

    private final Set<Character> validAfterOperationS = Set.of(
            '(', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'c', 'x', 'y', 'z', '-'
    );

    private final Set<Character> validAfterNotOperation = Set.of(
            ')', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '/', '\0', '^', '$'
    );

    private final Set<Character> validAfterVariable = Set.of(
            '+', '-', '*', '/', ')', '\0', '^', '$'
    );

    private String Print(CustomString cs) {
        StringBuilder sb = new StringBuilder();
        int l = cs.getPos() - 10;
        if (cs.getPos() - 10 < 0) {
            l = 0;
        }
        int r = cs.getPos() + 10;
        if (r > cs.length()) {
            r = cs.length();
        }
        for (int i = l; i < r; i++) {
            if (i == cs.getPos()) {
                sb.append("  !!!WRONG SYMBOL--> ").append(curr).append(" <--WRONG SYMBOL!!!  ");
            } else {
                sb.append(cs.giveElem(i));
            }
        }
        return sb.toString();
    }

    private void checkSymbol(char checkNow, char currS, char lastS, char last) throws ParserExceptions {
        if (checkNow == '$' || checkNow == '^') {
            throw new InputStringExceptions("wrong symbol" + " \"" + curr + "\" at position " + Print(cs));
        }
        if (lastS == ' ' && (Character.isDigit(last) && Character.isDigit(currS))) {
            throw new InputStringExceptions("spaces in numbers");
        }
        if (lastS == ' ' && last == currS && (last == '+' || last == '/' || last == '*')) {
            throw new ArgumentsExceptions("no middle argument");
        }
        if (lastS == ' ' && last != currS && (last == '-' || last == '+' || last == '/' || last == '*') &&
                (currS == '+' || currS == '*')) {
            throw new ArgumentsExceptions("no middle argument'");
        }
        if (lastS == '2' && (last == 'w' || last == 'g') &&
                (currS == 'x' || currS == 'y' || currS == 'z')) {
            throw new ArgumentsExceptions("log2x");
        }
    }

    private int update(char checkNow, int balance) {
        if (checkNow == '(') { balance++; }
        else if (checkNow == ')') { balance--; }
        return balance;
    }

    private CustomString makeCS(String s) throws ParserExceptions {
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        char last = 'h';
        for (int i = 0; i < s.length(); i++) {
            char checkNow = s.charAt(i);
            if (i != 0) {
                checkSymbol(checkNow, s.charAt(i), s.charAt(i - 1), last);
            } else if (checkNow == '$' || checkNow == '^'){
                throw new InputStringExceptions("start symbol");
            }
            if (!Character.isWhitespace(checkNow)) {
                if (i > 0 && checkNow == 'm') {
                    if (s.charAt(i + 1) == 'i') {
                        i += 2;
                        sb.append('$');
                        last = 'm';
                        continue;
                    } else {
                        i += 2;
                        sb.append('^');
                        last = 'm';
                        continue;
                    }
                }
                balance = update(checkNow, balance);
                if (balance < 0) {
                    throw new BracketsExceptions("no opening parenthesis");
                }
                if (!((last == 'w' || last == 'g') && checkNow == '2')) {
                    last = checkNow;
                }
                sb.append(checkNow);
            }
        }
        if (balance != 0) {
            throw new BracketsExceptions("no closing parenthesis");
        }
        return new CustomString(sb.toString());
    }

    private void getNext() {
        if (cs.hasNext()) {
            curr = cs.next();
        } else {
            curr = '\0';
        }
    }

    public ExpressionMode parse(String s, String mode) throws ParserExceptions {
        cs = makeCS(s);
        getNext();
        if (!validAfterOperationS.contains(curr)) {
            throw new InputStringExceptions("start symbol");
        }
        ExpressionMode ans = parseTrue(-1, mode);
        return ans;
    }

    private ExpressionMode parseTrue(int lvl, String mode) throws ParserExceptions {
        ExpressionMode expression = magic(mode);
        while (checkOperator(lvl)) {
            if (isBinary()) {
                char operator = curr;
                getNext();
                if (!validAfterOperationS.contains(curr)) {
                    throw new ArgumentsExceptions("no argument");
                }
                expression = makeOperation(operator, expression, parseTrue(priority.get(operator), mode), mode);
            } else {
                expression = magic(mode);
            }
        }
        return expression;
    }

    private boolean checkOperator(int lvl) {
        if (priority.containsKey(curr)) {
            return priority.get(curr) > lvl;
        }
        return curr != ')' && curr != '\0';
    }

    private boolean isBinary() {
        if (priority.containsKey(curr) && curr != 'l' && curr != 'p') {
            return true;
        } else {
            return false;
        }
    }

    private ExpressionMode magic(String mode) throws ParserExceptions {
        if (curr == '-') {
            getNext();
            if (!validAfterOperationS.contains(curr)) {
                throw new ArgumentsExceptions("empty minus");
            }
            if ('0' <= curr && curr <= '9') {
                return parseNumber(true, mode);
            } else {
                return workWithMinus(mode);
            }
        } else if ('0' <= curr && curr <= '9') {
            return parseNumber(false, mode);
        } else if (curr == '(') {
            getNext();
            if (!validAfterOperationS.contains(curr)) {
                throw new BracketsExceptions("no start element after open brackets");
            }
            ExpressionMode next = parseTrue(-1, mode);
            getNext();
            if (!validAfterNotOperation.contains(curr)) {
                throw new BracketsExceptions("no start element after open brackets");
            }
            return next;
        } else if (Character.isLetter(curr)) {
            if ('x' <= curr && curr <= 'z') {
                char var = curr;
                getNext();
                if (!validAfterVariable.contains(curr)) {
                    throw new InputStringExceptions("wrong symbol" + " \"" + curr + "\" at position " + Print(cs));
                }
                return new Variable(Character.toString(var));
            } else if (curr == 'c') {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 5; i++) {
                    sb.append(curr);
                    getNext();
                    if (curr == '\0') {
                        throw new ArgumentsExceptions("error in command" + " \"" + curr + "\" at position " + Print(cs));
                    }
                }
                if (sb.toString().equals("count")) {
                    if (mode.equals("i")) {
                        return new Count((TripleExpression) magic(mode));
                    } else if (mode.equals("d")) {
                        return new Count((ExpressionD) magic(mode));
                    } else if (mode.equals("bi")) {
                        return new Count((ExpressionBi) magic(mode));
                    } else if (mode.equals("s")) {
                        return new Count((ExpressionS) magic(mode));
                    } else if (mode.equals("l")) {
                        return new Count((ExpressionL) magic(mode));
                    } else {
                        return new Count((ExpressionU) magic(mode));
                    }
                }  else {
                    throw new ArgumentsExceptions("error in command" + " \"" + curr + "\" at position " + Print(cs));
                }
            } else {
                throw new InputStringExceptions("wrong symbol" +  " \"" + curr + "\" at position " + Print(cs));
            }
        } else {
            throw new ArgumentsExceptions("operation symbol is needless" +  " \"" + curr + "\" at position " + Print(cs));
        }
    }

    private TripleExpression parseNumber(boolean isMinus, String mode) throws InputStringExceptions {
        StringBuilder sb = new StringBuilder();
        if (isMinus) {
            sb.append("-");
        }
        int point = 0;
        while ('0' <= curr && curr <= '9' && point <= 1) {
            sb.append(curr);
            if (curr == '.' && mode.equals("d") && point == 0) {
                point++;
            } else if (curr == '.') {
                throw new InputStringExceptions("wrong symbol" + " \"" + curr + "\" at position " + Print(cs));
            }
            getNext();
            if (!validAfterNotOperation.contains(curr)) {
                throw new InputStringExceptions("wrong symbol" + " \"" + curr + "\" at position " + Print(cs));
            }
        }
        if (mode.equals("i")) {
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw new OverflowExceptions("const overflow");
            }
        } else if (mode.equals("d")){
            return new Const(Double.parseDouble(sb.toString()));
        } else if (mode.equals("bi")) {
            return new Const(new BigInteger(sb.toString()));
        } else if (mode.equals("s")) {
            return new Const(Short.parseShort(sb.toString()));
        } else if (mode.equals("l")) {
            return new Const(Long.parseLong(sb.toString()));
        } else {
            return new Const(Integer.parseInt(sb.toString()));
        }
    }

    private ExpressionMode makeOperation(char operator, ExpressionMode left, ExpressionMode right, String mode) {
        if (mode.equals("i")) {
            if (operator == '+') {
                return new CheckedAdd((TripleExpression) left, (TripleExpression) right);
            } else if (operator == '-') {
                return new CheckedSubtract((TripleExpression) left, (TripleExpression) right);
            } else if (operator == '*') {
                return new CheckedMultiply((TripleExpression) left, (TripleExpression) right);
            } else if (operator == '/') {
                return new CheckedDivide((TripleExpression) left, (TripleExpression) right);
            } else if (operator == '^') {
                return new Max((TripleExpression)left, (TripleExpression) right);
            } else if (operator == '$') {
                return new Min((TripleExpression) left, (TripleExpression) right);
            } else {
                throw new NullPointerException("wrong operation" + " \"" + curr + "\" at position " + Print(cs));
            }
        } else if (mode.equals("d")) {
            if (operator == '+') {
                return new CheckedAdd((ExpressionD) left, (ExpressionD) right);
            } else if (operator == '-') {
                return new CheckedSubtract((ExpressionD) left, (ExpressionD) right);
            } else if (operator == '*') {
                return new CheckedMultiply((ExpressionD) left, (ExpressionD) right);
            } else if (operator == '/') {
                return new CheckedDivide((ExpressionD) left, (ExpressionD) right);
            } else if (operator == '^') {
                return new Max((ExpressionD) left, (ExpressionD) right);
            } else if (operator == '$') {
                return new Min((ExpressionD) left, (ExpressionD) right);
            } else {
                return null;
            }
        } else if (mode.equals("bi")) {
            if (operator == '+') {
                return new CheckedAdd((ExpressionBi) left, (ExpressionBi) right);
            } else if (operator == '-') {
                return new CheckedSubtract((ExpressionBi) left, (ExpressionBi) right);
            } else if (operator == '*') {
                return new CheckedMultiply((ExpressionBi) left, (ExpressionBi) right);
            } else if (operator == '/') {
                return new CheckedDivide((ExpressionBi) left, (ExpressionBi) right);
            } else if (operator == '^') {
                return new Max((ExpressionBi) left, (ExpressionBi) right);
            } else if (operator == '$') {
                return new Min((ExpressionBi) left, (ExpressionBi) right);
            } else {
                return null;
            }
        } else if (mode.equals("s")) {
            if (operator == '+') {
                return new CheckedAdd((ExpressionS) left, (ExpressionS) right);
            } else if (operator == '-') {
                return new CheckedSubtract((ExpressionS) left, (ExpressionS) right);
            } else if (operator == '*') {
                return new CheckedMultiply((ExpressionS) left, (ExpressionS) right);
            } else if (operator == '/') {
                return new CheckedDivide((ExpressionS) left, (ExpressionS) right);
            } else if (operator == '^') {
                return new Max((ExpressionS) left, (ExpressionS) right);
            } else if (operator == '$') {
                return new Min((ExpressionS) left, (ExpressionS) right);
            } else {
                return null;
            }
        } else if (mode.equals("l")) {
            if (operator == '+') {
                return new CheckedAdd((ExpressionL) left, (ExpressionL) right);
            } else if (operator == '-') {
                return new CheckedSubtract((ExpressionL) left, (ExpressionL) right);
            } else if (operator == '*') {
                return new CheckedMultiply((ExpressionL) left, (ExpressionL) right);
            } else if (operator == '/') {
                return new CheckedDivide((ExpressionL) left, (ExpressionL) right);
            } else if (operator == '^') {
                return new Max((ExpressionL) left, (ExpressionL) right);
            } else if (operator == '$') {
                return new Min((ExpressionL) left, (ExpressionL) right);
            } else {
                return null;
            }
        } else {
            if (operator == '+') {
                return new CheckedAdd((ExpressionU) left, (ExpressionU) right);
            } else if (operator == '-') {
                return new CheckedSubtract((ExpressionU) left, (ExpressionU) right);
            } else if (operator == '*') {
                return new CheckedMultiply((ExpressionU) left, (ExpressionU) right);
            } else if (operator == '/') {
                return new CheckedDivide((ExpressionU) left, (ExpressionU) right);
            } else if (operator == '^') {
                return new Max((ExpressionU) left, (ExpressionU) right);
            } else if (operator == '$') {
                return new Min((ExpressionU) left, (ExpressionU) right);
            } else {
                return null;
            }
        }
    }

    private ExpressionMode workWithMinus(String mode) throws ParserExceptions {
        int cnt = 1;
        while (curr == '-') {
            cnt++;
            getNext();
        }
        if (cnt % 2 == 1) {
            if (mode.equals("i")) {
                return new CheckedMinus((TripleExpression) magic(mode));
            } else if (mode.equals("d")) {
                return new CheckedMinus((ExpressionD) magic(mode));
            } else if (mode.equals("bi")){
                return new CheckedMinus((ExpressionBi) magic(mode));
            } else if (mode.equals("s")) {
                return new CheckedMinus((ExpressionS) magic(mode));
            } else if (mode.equals("l")) {
                return new CheckedMinus((ExpressionL) magic(mode));
            } else {
                return new CheckedMinus((ExpressionU) magic(mode));
            }
        } else {
            return magic(mode);
        }
    }

}
