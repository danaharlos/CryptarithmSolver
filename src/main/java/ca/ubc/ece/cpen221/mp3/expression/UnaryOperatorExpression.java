package ca.ubc.ece.cpen221.mp3.expression;

import ca.ubc.ece.cpen221.mp3.operator.UnaryOperator;

public class UnaryOperatorExpression implements Expression{
    private UnaryOperator operator;
    private Expression operand;
    /*
Rep Invariant:
-UnaryOperatorExpression is never null

Abstraction Function:
Represents an immutable unary operation (ie -( ), | |) on an expressions which may or may not
be a composition of expressions.
 */
    /**
     * Create a new Unary Expression
     * @param operator The operator to apply to the operand, must not be null.
     * @param operand  An expression to be manipulated, must not be null.
     */
    public UnaryOperatorExpression(UnaryOperator operator, Expression operand){
        this.operator = operator;
        this.operand = operand;
    }

    /**
     * Evaluates the Unary expression.
     * Requires: If either operand is a VariableExpression or contains a VariableExpression,
     * the variables must have stored value
     * @return The value of the expression.
     */
    @Override
    public double eval() {
        return operator.apply(operand.eval());
    }

    /**
     * The string of the expression
     * @return The expression as a string.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int whiteSpaceIndex = 0;
        String op = operator.toString();
        for(int i = 0; i < op.length(); i++){
            if(op.charAt(i) == ' ')  whiteSpaceIndex = i;
        }
        sb.append(op.substring(0,whiteSpaceIndex));
        sb.append(operand.toString());
        sb.append(op.substring(whiteSpaceIndex+1,op.length()));
        return sb.toString();
    }


}
