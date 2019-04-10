package ca.ubc.ece.cpen221.mp3.expression;

/**
 * NumberExpression - Expresses a number as an expression.
 *
 */
public class NumberExpression implements Expression {
/*
Rep Invariant:
-NumberExpression must maintain immutability throughout all methods.
-NumberExpression must not be null

Abstract Function:
Represents an immutable expression representation of any number

*/


    private final double value; //The value of the expression.

    /**
     * Create a expression representing a number.
     * @param value value of expression.
     */
    public NumberExpression(double value) {
        this.value = value;
    }

    /**
     * Returns the number
     * @return the number
     */
    @Override
    public double eval() {
        return value;
    }

    /**
     * Returns the value as a string.
     * @return String, value.
     */
    @Override
    public String toString() {
        return Double.toString(value);
    }

}
