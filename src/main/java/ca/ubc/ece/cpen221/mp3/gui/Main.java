package ca.ubc.ece.cpen221.mp3.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import ca.ubc.ece.cpen221.mp3.operator.*;

/**
 * Main - creates and runs a new Calculator GUI. 
 *
 */
public class Main {

	/**
	 * Runs the calculator GUI.
	 * 
	 * @param args arguments to the main function 
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> createAndShowSetupScreen());
	}
	
	private static void createAndShowSetupScreen() {
		List<Operator> operators = new ArrayList<>();
		BinaryOperator add = new AdditionOperator();
		BinaryOperator sub = new SubtractionOperator();
		BinaryOperator mul = new MultiplicationOperator();
		BinaryOperator div = new DivisionOperator();
		BinaryOperator pow = new ExponentOperator();
		UnaryOperator neg = new NegationOperator();
		UnaryOperator abs = new AbsoluteOperator();
		operators.add(add);
		operators.add(sub);
		operators.add(mul);
		operators.add(div);
		operators.add(pow);
		operators.add(neg);
		operators.add(abs);

		Calculator calculator = new Calculator(operators);
		calculator.launch();
	}

}
