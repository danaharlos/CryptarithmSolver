package ca.ubc.ece.cpen221.mp3.operator;

public class  ExponentOperator implements BinaryOperator {

    /*
    Rep Invariant:
    -ExponentOperator must not be null

    Abstract Function:
    Represents the operation Math.pow(arg1, arg2)
     */
    /**
     * Creates a new Exponent Operator
     */
    public ExponentOperator(){

    }

    /**
     * Returns the string "^".
     * @return String, "^"
     */
    @Override
    public String toString() {
        return "^";
    }

    /**
     * Calculates the result of raising arg1 to the power of arg2.
     * @param arg1 the first number before the operator
     * @param arg2 the second number after the operator
     * @return the result of raising arg1 to the power of arg2
     */
    @Override
    public double apply(double arg1, double arg2) {
        return Math.pow(arg1, arg2);
    }

}
