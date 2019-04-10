package ca.ubc.ece.cpen221.mp3.expression;

import ca.ubc.ece.cpen221.mp3.operator.BinaryOperator;

public class BinaryOperatorExpression implements Expression{
    private BinaryOperator operator;
    private Expression operand1;
    private Expression operand2;
/*
Rep Invariant:
-BinaryOperatorExpression is never null

Abstraction Function:
Represents an immutable binary operation (ie +, -, *, /, ^) on two expressions which may or may not
be a composition of expressions.
 */
    /**
     * Create a new Binary Expression
     * @param operator The operator to apply to the operand, must not be null.
     * @param operand1  An expression to be manipulated, must not be null.
     * @param operand2  An expression to be manipulated, must not be null.
     */
    public BinaryOperatorExpression(BinaryOperator operator, Expression operand1, Expression operand2){
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    /**
     * Evaluates the Binary expression.
     * Requires: If either operand is a VariableExpression or contains a VariableExpression,
     * the variables must have stored value
     * @return The value of the expression.
     */
    @Override
    public double eval() {
        return operator.apply(operand1.eval(), operand2.eval());
    }

    /**
     * The string of the expression
     * @return The expression as a string.
     */
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        if(operand1 instanceof VariableExpression)
            sb.append(((VariableExpression) operand1).name());
        else
            sb.append(operand1.toString());

        sb.append(operator.toString());

        if(operand2 instanceof VariableExpression)
            sb.append(((VariableExpression) operand2).name());
        else
            sb.append(operand2.toString());

        return sb.toString();
    }


}

