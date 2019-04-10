package ca.ubc.ece.cpen221.mp3.operator;

public class  MultiplicationOperator implements BinaryOperator {

    /*
    Rep Invariant:
    -MultiplicationOperator must not be null

    Abstract Function:
    Represents the multiplication of arguments arg1 and arg2
     */
    /**
     * Creates a new  Multiplication Operator
     */
    public MultiplicationOperator(){

    }

    /**
     * Returns the string "*".
     * @return String, "*"
     */
    @Override
    public String toString() {
        return "*";
    }

    /**
     * Applies Multiplication operator to two operands and returns the result of this Multiplication.
     * @param arg1 the first number before the operator
     * @param arg2 the second number after the operator
     * @return the result of  Multiplying arg1 and arg2
     */
    @Override
    public double apply(double arg1, double arg2) {

        return arg1*arg2;
    }

}