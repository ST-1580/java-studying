package expression.generic;


import expression.*;
import expression.exceptions.*;
import expression.parser.ExpressionParser;
import expression.types.*;

import java.util.Map;

public class GenericTabulator implements Tabulator {

    private final Map<String, ExpressionType<? extends Number>> types = Map.of(
            "i", new IntegerType(true),
            "d", new DoubleType(),
            "bi", new BigIntegerType(),
            "s", new ShortType(),
            "l", new LongType(),
            "u", new IntegerType(false)
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        ExpressionType<? extends Number> type = types.get(mode);
        return makeTable(type, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T extends Number> Object[][][] makeTable(ExpressionType<T> type, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParserExceptions {
        ExpressionParser<T> myParser = new ExpressionParser<>(type);
        TripleExpression<T> result = myParser.parse(expression);
        Object[][][] ans = new Object[x2 -x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int q = z1; q <= z2; q++) {
                    ans[i -x1][j - y1][q - z1] = null;
                    T x = type.parse(Integer.toString(i));
                    T y = type.parse(Integer.toString(j));
                    T z = type.parse(Integer.toString(q));
                    try {
                        ans[i -x1][j - y1][q - z1] = result.evaluate(x, y, z);
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        return ans;
    }


}
