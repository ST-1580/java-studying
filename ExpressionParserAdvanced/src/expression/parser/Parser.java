package expression.parser;


import expression.TripleExpression;
import expression.exceptions.ArgumentsExceptions;
import expression.exceptions.BracketsExceptions;
import expression.exceptions.InputStringExceptions;
import expression.exceptions.ParserExceptions;

public interface Parser {
    TripleExpression parse(String expression) throws ParserExceptions;
}