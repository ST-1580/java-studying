package expression.parser;

import expression.*;
import expression.exceptions.*;

import java.util.Map;
import java.util.Set;

public class ExpressionParser implements Parser {
    private char curr;
    private CustomString cs;


    private final Map<Character, Integer> priority = Map.of(
            '<', 1,
            '>', 1,
            '+', 2,
            '-', 2,
            '*', 3,
            '/', 3,
            'l', 4,
            'p', 4,
            '$', 4,
            '^', 4
    );

    private final Set<Character> validAfterOperationF = Set.of(
            '(', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'l', 'p', 'x', 'y', 'z'
    );

    private final Set<Character> validAfterOperationS = Set.of(
            '(', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'l', 'p', 'x', 'y', 'z', '-'
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
                if (i > 0 && checkNow == '*' && s.charAt(i - 1) == '*') {
                    last = checkNow;
                    sb.delete(sb.length() - 1, sb.length());
                    sb.append("^");
                    continue;
                }
                if (i > 0 && checkNow == '/' && s.charAt(i - 1) == '/') {
                    last = checkNow;
                    sb.delete(sb.length() - 1, sb.length());
                    sb.append("$");
                    continue;
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

    public TripleExpression parse(String s) throws ParserExceptions {
            cs = makeCS(s);
            getNext();
            if (!validAfterOperationS.contains(curr)) {
                throw new InputStringExceptions("start symbol");
            }
            TripleExpression ans = parseTrue(-1);
            return ans;
    }

    private TripleExpression parseTrue(int lvl) throws ParserExceptions {
        TripleExpression expression = magic();
        while (checkOperator(lvl)) {
            if (isBinary()) {
                char operator = curr;
                getNext();
                if (!validAfterOperationS.contains(curr)) {
                    throw new ArgumentsExceptions("no argument");
                }
                expression = makeOperation(operator, expression, parseTrue(priority.get(operator)));
            } else {
                expression = magic();
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

    private TripleExpression magic() throws ParserExceptions {
        if (curr == '-') {
            getNext();
            if (!validAfterOperationS.contains(curr)) {
                throw new ArgumentsExceptions("empty minus");
            }
            if ('0' <= curr && curr <= '9') {
                return parseNumber(true);
            } else {
                return workWithMinus();
            }
        } else if ('0' <= curr && curr <= '9') {
            return parseNumber(false);
        } else if (curr == '(') {
            getNext();
            if (!validAfterOperationS.contains(curr)) {
                throw new BracketsExceptions("no start element after open brackets");
            }
            TripleExpression next = parseTrue(-1);
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
            } else if (curr == 'l' || curr == 'p') {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    sb.append(curr);
                    getNext();
                    if (curr == '\0') {
                        throw new ArgumentsExceptions("error in command" + " \"" + curr + "\" at position " + Print(cs));
                    }
                }
                if (sb.toString().equals("log2")) {
                    return new Log2(magic());
                } else if (sb.toString().equals("pow2")) {
                    return new Pow2(magic());
                } else {
                    throw new ArgumentsExceptions("error in command" + " \"" + curr + "\" at position " + Print(cs));
                }
            } else {
                throw new InputStringExceptions("wrong symbol" +  " \"" + curr + "\" at position " + Print(cs));
            }
        } else {
            throw new ArgumentsExceptions("operation symbol is needless" +  " \"" + curr + "\" at position " + Print(cs));
        }
    }

    private TripleExpression parseNumber(boolean isMinus) throws InputStringExceptions {
        StringBuilder sb = new StringBuilder();
        if (isMinus) {
            sb.append("-");
        }
        while ('0' <= curr && curr <= '9') {
            sb.append(curr);
            getNext();
            if (!validAfterNotOperation.contains(curr)) {
                throw new InputStringExceptions("wrong symbol" + " \"" + curr + "\" at position " + Print(cs));
            }
        }
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new OverflowExceptions("const overflow");
        }
    }

    private TripleExpression makeOperation(char operator, TripleExpression left, TripleExpression right) {
        if (operator == '+') {
            return new CheckedAdd(left, right);
        } else if (operator == '-') {
            return new CheckedSubtract(left, right);
        } else if (operator == '*') {
            return new CheckedMultiply(left, right);
        } else if (operator == '/') {
            return new CheckedDivide(left, right);
        } else if (operator == '^') {
            return new Pow(left, right);
        } else if (operator == '$') {
            return new Log(left, right);
        } else {
            throw new NullPointerException("wrong operation" + " \"" + curr + "\" at position " + Print(cs));
        }
    }

    private TripleExpression workWithMinus() throws ParserExceptions {
        int cnt = 1;
        while (curr == '-') {
            cnt++;
            getNext();
        }
        if (cnt % 2 == 1) {
            return new CheckedMinus(magic());
        } else {
            return magic();
        }
    }
}
