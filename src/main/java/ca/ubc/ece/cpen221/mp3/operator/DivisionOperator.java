package ca.ubc.ece.cpen221.mp3.operator;

public class  DivisionOperator implements BinaryOperator {

    /*
    Rep Invariant:
    -DivisionOperator must not be null

    Abstract Function:
    Represents the operation arg1 / arg2
     */
    /**
     * Creates a new Division Operator
     */
    public DivisionOperator(){

    }

    /**
     * Returns the string "/".
     * @return String, "/"
     */
    @Override
    public String toString() {
        return "/";
    }

    /**
     * Applies Division operator to two operands and returns the result of this Division.
     * @param arg1 the numerator
     * @param arg2 the denominator
     * @return the result of  Dividing arg1 and arg2
     */
    @Override
    public double apply(double arg1, double arg2) {

        return arg1/arg2;
    }

}