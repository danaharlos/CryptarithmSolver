/**
 * A simple command line parser for arithmetic expressions
 */
package ca.ubc.ece.cpen221.mp3.parser;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import ca.ubc.ece.cpen221.mp3.expression.Expression;
import ca.ubc.ece.cpen221.mp3.operator.*;

/**
 * CommandLineParser - a command line calculator.
 * 
 * You will need to add any new Operators you create to the operatorSet or they
 * will not be usable in the command line calculator.
 *
 */
public class CommandLineParser {

	/**
	 * @param args
	 *            program arguments
	 */
	public static void main(String[] args) {

		Set<Operator> operatorSet = new HashSet<Operator>();

		BinaryOperator add = new AdditionOperator();
		BinaryOperator sub = new SubtractionOperator();
		BinaryOperator mul = new MultiplicationOperator();
		BinaryOperator div = new DivisionOperator();
		BinaryOperator pow = new ExponentOperator();
		UnaryOperator neg = new NegationOperator();
		UnaryOperator abs = new AbsoluteOperator();
		operatorSet.add(add);
		operatorSet.add(sub);
		operatorSet.add(mul);
		operatorSet.add(div);
		operatorSet.add(pow);
		operatorSet.add(neg);
		operatorSet.add(abs);


		ExpressionParser parser = new ExpressionParser(operatorSet, new ExpressionMaker());

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Enter an expression");
			String expression = scanner.nextLine();
			try {
				Expression exp = parser.parse(expression);
				System.out.println("Result: " + exp.eval());
			} catch (Exception e) {
				System.out.println("Input format not accepted. Please try again.");
			}
		} while (true);

	}

}
