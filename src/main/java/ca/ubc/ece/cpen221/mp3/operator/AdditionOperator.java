package ca.ubc.ece.cpen221.mp3.operator;

public class AdditionOperator implements BinaryOperator {
    /**
     * Creates a new Addition Operator
     */
    /*
    Rep Invariant:
    -AdditionOperator must not be null

    Abstract Function:
    Represents the operation + on arguments arg1 and arg2
     */

    public AdditionOperator(){

    }

    /**
     * Returns the string "+".
     * @return String, "+"
     */
    @Override
    public String toString() {
        return "+";
    }

    /**
     * Applies addition operator to two operands and returns the result of this addition.
     * @param arg1 the first number before the operator
     * @param arg2 the second number after the operator
     * @return the result of adding arg1 and arg2
     */
    @Override
    public double apply(double arg1, double arg2) {

        return arg1+arg2;
    }

}
