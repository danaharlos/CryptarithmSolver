package ca.ubc.ece.cpen221.mp3.operator;

public class SubtractionOperator implements BinaryOperator {

    /*
    Rep Invariant:
    -SubtractionOperator must not be null

    Abstract Function:
    Represents the operation arg1 - arg2
     */
    /**
     * Creates a new Subtraction Operator
     */
    public SubtractionOperator(){

    }

    /**
     * Returns the string "-".
     * @return String, "-"
     */
    @Override
    public String toString() {
        return "-";
    }

    /**
     * Applies subtraction operator to two operands and returns the result of this subtraction.
     * @param arg1 the first number before the operator
     * @param arg2 the second number after the operator
     * @return the result of subtracting arg1 and arg2
     */
    @Override
    public double apply(double arg1, double arg2) {

        return arg1-arg2;
    }

}
