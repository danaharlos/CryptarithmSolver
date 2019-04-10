package ca.ubc.ece.cpen221.mp3.operator;

import ca.ubc.ece.cpen221.mp3.expression.Expression;
import ca.ubc.ece.cpen221.mp3.parser.ExpressionMaker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OperatorTest {
    ExpressionMaker maker = new ExpressionMaker();
    private final Expression num3 = maker.createNumberExpression(3.0);
    private final Expression num2 = maker.createNumberExpression(2.0);
    private final Expression numN7 = maker.createNumberExpression(-7.0);

    @Test
    public void test1(){

        BinaryOperator add = new AdditionOperator();
        BinaryOperator sub = new SubtractionOperator();
        BinaryOperator mul = new MultiplicationOperator();
        BinaryOperator div = new DivisionOperator();
        UnaryOperator abs = new AbsoluteOperator();
        UnaryOperator neg = new NegationOperator();
        BinaryOperator pow = new ExponentOperator();

        Expression exp1 = maker.createBinaryOperationExpression(add, num3, num2);
        Expression exp2 = maker.createBinaryOperationExpression(sub, num3, num2);
        Expression exp3 = maker.createBinaryOperationExpression(mul, num3, num2);
        Expression exp4 = maker.createBinaryOperationExpression(div, num3, num2);
        Expression exp5 = maker.createUnaryOperationExpression(abs,numN7);
        Expression exp6 = maker.createUnaryOperationExpression(neg,numN7);
        Expression exp7 = maker.createBinaryOperationExpression(pow,numN7,num2);

        assertEquals("+",add.toString());
        assertEquals("-",sub.toString());
        assertEquals("*",mul.toString());
        assertEquals("/",div.toString());
        assertEquals("| |",abs.toString());
        assertEquals("-( )",neg.toString());
        assertEquals("^",pow.toString());

        assertTrue(exp1.eval() == 5.0);
        assertTrue(exp2.eval() == 1.0);
        assertTrue(exp3.eval() == 6.0);
        assertTrue(exp4.eval() == 1.5);
        assertTrue(exp5.eval() == 7.0);
        assertTrue(exp6.eval() == 7.0);
        assertTrue(exp7.eval() == 49.0);

    }
}
