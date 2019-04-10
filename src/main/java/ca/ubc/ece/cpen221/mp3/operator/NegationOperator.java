package ca.ubc.ece.cpen221.mp3.operator;

public class NegationOperator implements UnaryOperator {

    /*
    Rep Invariant:
    -NegationOperator must not be null

    Abstract Function:
    Represents the negation of the argument
     */
    /**
     * Creates a new Negation Operator
     */
    public NegationOperator(){
    }

    /**
     * Returns the string "-( )".
     * @return String, the symbol of the operator with a space in between.
     */
    @Override
    public String toString() {
        return "-( )";
    }

    /**
     * Negates value of given value.
     * @param arg1 number to find negate value of.
     * @return the negation value of arg1.
     */
    @Override
    public double apply(double arg1) {
        return arg1*-1;
    }

}
