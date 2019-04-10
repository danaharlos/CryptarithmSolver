package ca.ubc.ece.cpen221.mp3.operator;

public class AbsoluteOperator implements UnaryOperator {
    /**
     * Creates a new Absolute Operator
     */

    /*
    Rep Invariant:
    -AbsoluteOperator must not be null

    Abstract Function:
    Represents the operation Math.abs( ) on an argument
     */
    public AbsoluteOperator(){

    }

    /**
     * Returns the string "| |".
     * @return String, the symbol of the operator with a space in between.
     */
    @Override
    public String toString() {
        return "| |";
    }

    /**
     * Gets absolute value of given value.
     * @param arg1 number to find absolute value of.
     * @return the absolute value of arg1.
     */
    @Override
    public double apply(double arg1) {
        return Math.abs(arg1);
    }

}
