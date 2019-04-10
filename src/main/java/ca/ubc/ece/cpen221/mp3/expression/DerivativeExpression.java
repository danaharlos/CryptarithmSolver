package ca.ubc.ece.cpen221.mp3.expression;

/**
 * DerivativeExpression - The derivative of specific function.
 *
 */
public class DerivativeExpression implements Expression { //does not implement toString

	/*
	Rep Invariant:
	-DerivativeExpression must represent an expression which can be derived through Newton's Method
	-DerivativeExpression must not be null
	-all VariableExpressions that DerivativeExpression is composed of must have a stored value

	Abstract Function:
	Represents the derivative of an expression containing some variable to be derived with respect to
	 */
	/**
	 * Create an expression representing the derivative of the specified
	 * function with respect to the specified variable.
	 * 
	 * @param fn the function whose derivative this expression represents
	 * @param independentVar the variable with respect to which we're
	 * 		  differentiating
	 */
	private final double DELTA_X=Math.pow(10, -9);
	private Expression fn;
	private VariableExpression independentVar;

	/**
	 * Precondition: IndependentVariable has an assigned value. fn must contain independentVar, fn must
	 * be able to be derived by Newton's method
	 * @param fn The function whose derivative this expression represents.
	 * @param independentVar The variable to derive with respect to.
	 */
	public DerivativeExpression(Expression fn, VariableExpression independentVar) {
		this.fn=fn;
		this.independentVar=independentVar;

	}

	/**
	 * Calculates the value of the derivative at the value of the independent variable.
	 * @return Double, The value of the derivative.
	 */
	@Override
	public double eval() {
		double fnDeltaVal, fnVal;
		double originalIndepVarVal=independentVar.eval();

		fnVal=fn.eval();

		independentVar.store(originalIndepVarVal+DELTA_X);

		fnDeltaVal=fn.eval();

		independentVar.store(originalIndepVarVal);

		return ((fnDeltaVal-fnVal)/DELTA_X);
	}

	/**
	 * Returns a zero of the specified function using
	 * Newton’s method with approxZero as the initial estimate.
	 *
	 * @param fn the function whose zero is to be found
	 * @param x the independent variable of the function
	 * @param approxZero initial approximation for the
	 *        zero of the function
	 * @param tolerance how close zero the returned
	 *        zero has to be
	 */
	public double zero(Expression fn,VariableExpression x, double approxZero, double tolerance){

		x.store(approxZero);

		DerivativeExpression fnDerivative = new DerivativeExpression(fn,x);

		double fnDerivativeVal = fnDerivative.eval();

		double approx = approxZero - fn.eval()/fnDerivativeVal;

		if(Math.abs(approx - approxZero) < tolerance) return approx;

		return zero(fn,x,approx,tolerance);


	}


}
