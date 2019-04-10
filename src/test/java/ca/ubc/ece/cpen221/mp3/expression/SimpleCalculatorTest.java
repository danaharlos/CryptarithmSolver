package ca.ubc.ece.cpen221.mp3.expression;


import ca.ubc.ece.cpen221.mp3.cryptarithm.Cryptarithm;
import ca.ubc.ece.cpen221.mp3.cryptarithm.NoSolutionException;
import ca.ubc.ece.cpen221.mp3.operator.*;
import ca.ubc.ece.cpen221.mp3.parser.ExpressionMaker;
import ca.ubc.ece.cpen221.mp3.permutation.Permutation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class SimpleCalculatorTest {
    private ExpressionMaker maker= new ExpressionMaker();;
    private final Expression num3 = maker.createNumberExpression(3.0);
    private final Expression num2 = maker.createNumberExpression(2.0);
    private final Expression numN7 = maker.createNumberExpression(-7.0);
    private final Expression num1 = maker.createNumberExpression(1);
    private BinaryOperator add, sub,mul ,div, pow;
    private UnaryOperator abs,neg;
    private Expression exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, xPlusThree, xMinusOne, quadratic, xCubed, xCubedPlusOne, xMinusTwo, full;
    private String variableName;
    private VariableExpression variable, ex;
    private double variableValue;
    private final double tolerance = 1e-5;
    private Expression fn1, numExp, fn;
    private DerivativeExpression d;


    /**
     * initializes objects to be used throughout tests
     */
    @Before
    public void initialize(){

        add = new AdditionOperator();
        sub = new SubtractionOperator();
        mul = new MultiplicationOperator();
        div = new DivisionOperator();
        abs = new AbsoluteOperator();
        neg = new NegationOperator();
        pow = new ExponentOperator();

        exp1 = maker.createBinaryOperationExpression(add, num3, num2);
        exp2 = maker.createBinaryOperationExpression(sub, num3, num2);
        exp3 = maker.createBinaryOperationExpression(mul, num3, num2);
        exp4 = maker.createBinaryOperationExpression(div, num3, num2);
        exp5 = maker.createUnaryOperationExpression(abs,numN7);
        exp6 = maker.createUnaryOperationExpression(neg,numN7);
        exp7 = maker.createBinaryOperationExpression(pow,numN7,num2);
        exp8 = maker.createBinaryOperationExpression(add,exp6,exp2);

    }

    /**
     * Tests toString() method for number expressions
     */
    @Test
    public void testNumExp() {
        assertEquals(num3.toString(), "3.0");
        assertEquals(num2.toString(), "2.0");
        assertEquals(numN7.toString(), "-7.0");
        assertEquals(num1.toString(), "1.0");
    }

    /**
     * Tests toString() method for operator expressions
     */
    @Test
    public void testOperators() {
        assertEquals(add.toString(), "+");
        assertEquals(sub.toString(), "-");
        assertEquals(mul.toString(), "*");
        assertEquals(div.toString(), "/");
        assertEquals(pow.toString(), "^");
        assertEquals(neg.toString(), "-( )");
        assertEquals(abs.toString(), "| |");
    }

    /**
     * Tests basic arithmetic using number expressions and operators +, -, *, /, ^, -( ), | |
     */
	@Test
	public void testSimpleArithmetic() {
        assertEquals("3.0+2.0", exp1.toString());
        assertEquals("3.0-2.0", exp2.toString());
        assertEquals("3.0*2.0", exp3.toString());
        assertEquals("3.0/2.0", exp4.toString());
        assertEquals("|-7.0|", exp5.toString());
        assertEquals("-(-7.0)", exp6.toString());
        assertEquals("-7.0^2.0", exp7.toString());


        assertTrue(exp1.eval() == 5.0);
        assertTrue(exp2.eval() == 1.0);
        assertTrue(exp3.eval() == 6.0);
        assertTrue(exp4.eval() == 1.5);
        assertTrue(exp5.eval() == 7.0);
        assertTrue(exp6.eval() == 7.0);
        assertTrue(exp7.eval() == 49.0);

    }

    /**
     * Tests creation and use of all variable functions -> store, eval, name, toString
     * variable: x = 1.0
     */
    @Test
    public void testVariableExp() {
        variableName = "x";
        variable = new VariableExpression(variableName);
        variableValue = 1.0;
        variable.store(variableValue);
        assertTrue(variable.eval() == 1.0);
        assertEquals(variable.name(), "x");
        assertEquals(variable.toString(), "x=1.0");
    }
    @Test
    public void testExpToString(){
        ex = new VariableExpression("x");
        ex.store(1);
        xPlusThree = maker.createBinaryOperationExpression(add, ex, num3); //makes x+3
        xMinusOne = maker.createBinaryOperationExpression(sub, num1, ex); //makes x-1

        assertEquals(xPlusThree.toString(), "x+3.0");
        assertEquals(xMinusOne.toString(), "1.0-x");

    }

    /**
     * Calculates the derivative and finds zeros of the variable function x * x - 2.0
     * where variable x = 1.0
     */
    @Test
    public void testDeriv1() {
        variableName = "x";
        variable = new VariableExpression(variableName);
        variableValue = 1.0;
        variable.store(variableValue);
        fn1 = maker.createBinaryOperationExpression(mul, variable, variable); //make x^2
        numExp = maker.createNumberExpression(2.0);
        fn = maker.createBinaryOperationExpression(sub, fn1, numExp);//make x^2 - 2
        d = new DerivativeExpression(fn, variable);
        double dVal = d.eval(); //evaluate deriv of expression when x=1

        assertTrue(Math.abs(dVal - 2.0) <= tolerance);
        assertTrue(Math.abs(d.zero(fn, variable, -100, tolerance) + Math.sqrt(2)) <= tolerance);
    }

    /**
     * Calculates the derivative and finds zeros of the variable function (x-1)(x+3)
     * where variable x = 2.0
     */
    @Test
    public void testDeriv2() {
        ex = new VariableExpression("x");
        ex.store(2);
        xPlusThree = maker.createBinaryOperationExpression(add, ex, num3); //makes x+3
        xMinusOne = maker.createBinaryOperationExpression(sub, ex, num1); //makes x-1
        quadratic = maker.createBinaryOperationExpression(mul, xPlusThree, xMinusOne); //makes (x-1)(x+3)

        //Tests (x+3)(x-1) -> find zero of -3 when approxZero is -10, calculates value of derivative when x=2 to be ~6
        DerivativeExpression dervQuad = new DerivativeExpression(quadratic, ex);

        assertTrue(Math.abs(dervQuad.eval() - 6) <= tolerance);
        assertTrue(Math.abs(dervQuad.zero(quadratic, ex, -10, tolerance) + 3) <= tolerance);
    }

    /**
     * Calculates derivative and zero of cubic function with asymptote
     * function = (x^3 + 1)/(x - 2) when x=1 and when x=2(the asymptote) to see what happens
     */
    @Test
    public void testDeriv3(){
        ex = new VariableExpression("x");
        ex.store(1);
        xCubed= maker.createBinaryOperationExpression(pow, ex, num3);
        xCubedPlusOne=maker.createBinaryOperationExpression(add, xCubed, num1);
        xMinusTwo=maker.createBinaryOperationExpression(sub, ex, num2);
        full=maker.createBinaryOperationExpression(div, xCubedPlusOne, xMinusTwo);

        DerivativeExpression deriv= new DerivativeExpression(full, ex);

        assertTrue(Math.abs(deriv.eval() + 5) <= tolerance);
        assertTrue(Math.abs(deriv.zero(full, ex, 0, tolerance) + 1) <= tolerance);

        ex.store(2);
        //System.out.println(deriv.eval() );


    }

    /**
     * Evaluate derivative when function is just a number, -7
     */
    @Test
    public void testDeriv4(){
        ex = new VariableExpression("x");
        ex.store(1);

        DerivativeExpression deriv= new DerivativeExpression(numN7, ex);
        assertTrue(Math.abs(deriv.eval()) <= tolerance); //check that it =0

        //If you try to find zero of this function,Stack Overflow Exception
        //System.out.println(deriv.zero(numN7, ex, 1, tolerance));

    }



/*
    @Test
    public void test() {
        //51304.0 / 61904.0 - 7260.0 / 8760.0
        ExpressionMaker maker = new ExpressionMaker();
        List<VariableExpression> varList = makeVariables(new String[] {"N","O","R","T","H","S","U","E","A","W"});
        List<Double> values = new ArrayList<>();
        Collections.addAll(values,5.0,1.0,3.0,0.0,4.0,6.0,9.0,7.0,2.0,8.0);

        storeVar(varList,values);

        NumberExpression num1 = new NumberExpression(51304.0);
            NumberExpression num2 = new NumberExpression(61904.0);
            NumberExpression num3 = new NumberExpression(7260.0);
            NumberExpression num4 = new NumberExpression(8760.0);
            Expression north = makeWordExpression("NORTH",varList);
            Expression south = makeWordExpression("SOUTH",varList);
            Expression east = makeWordExpression("EAST",varList);
            Expression west = makeWordExpression("WEST",varList);

        System.out.println(north.toString() + " EVAL: " + north.eval());
        System.out.println(south.toString() + " EVAL: " + south.eval());
        System.out.println(east.toString() + " EVAL: " + east.eval());
        System.out.println(west.toString() + " EVAL: " + west.eval());

            BinaryOperator sub = new SubtractionOperator();
            BinaryOperator div = new DivisionOperator();

            Expression e1 = maker.createBinaryOperationExpression(div, north, south);
            Expression e2 = maker.createBinaryOperationExpression(div, east, west);
            Expression finalE = maker.createBinaryOperationExpression(sub, e1, e2);

            Expression e11 = maker.createBinaryOperationExpression(div, north, south);
            Expression e22 = maker.createBinaryOperationExpression(sub, e11, east);
            Expression finalE1 = maker.createBinaryOperationExpression(div, e22, west);

        System.out.println(finalE.toString() + " Eval: " + finalE.eval());
        System.out.println(finalE.toString() + " Eval2: " + finalE1.eval());


        }


        private void storeVar(List<VariableExpression> list, List<Double> values){
	    for(int i = 0; i < list.size(); i++ ){
	        list.get(i).store(values.get(i));
        }
        }


    private List<VariableExpression> makeVariables(String[] letters){
	    List<VariableExpression> list = new ArrayList<>();
	    for(String letter : letters){
	        VariableExpression ve = new VariableExpression(letter);
	        list.add(ve);
        }
        return list;
    }


    private Expression makeWordExpression(String word,List<VariableExpression> varList ){

	    List<Expression> tens = makeTens();

        BinaryOperator add = new AdditionOperator();

        BinaryOperator mul = new MultiplicationOperator();

        ExpressionMaker maker = new ExpressionMaker();
        List<VariableExpression> varExpList = new ArrayList<>();
        //Creating a list of variableExpressions whose names are the individual letters.
        boolean alreadyExists=false;

        for(int i = 0; i < word.length(); i++){

            for(VariableExpression ve: varList) {
                if (Character.toString(word.charAt(i)).equals(ve.name())) {
                    alreadyExists=true;
                    varExpList.add(ve);
                }
            }

            if(!alreadyExists){
                VariableExpression nve = new VariableExpression(word.substring(i, i + 1));
                varExpList.add(nve);
            }

            alreadyExists=false;
        }
        List<Expression> tensVariableList = new ArrayList<>();
        //wordToLetterList.add(varExpList); //Add the wordToLetter list to the list of lists.
        int count = varExpList.size()-1;
        for(int j = 0; j <  varExpList.size(); j++){
            Expression boe = maker.createBinaryOperationExpression(mul,varExpList.get(j),tens.get(count));
            tensVariableList.add(boe);
            count--;
        }

        return createBigExpression(tensVariableList,add);
    }


    /**
     * Fill the tens list with the appropriate amount of powers of 10.
     * Requires nothing.

    private List<Expression> makeTens(){
        List<Expression> list = new ArrayList<>();

        int big = 10;


        int multiplier = 1;
        //Make the List
        for(int i = 0; i < big; i++){
            NumberExpression ne = new NumberExpression(multiplier);
            list.add(ne);
            multiplier *= 10;
        }
        return list;
    }


    private Expression createBigExpression(List<Expression> list,BinaryOperator op){
    ExpressionMaker maker = new ExpressionMaker();
        if(list.size()==1) return list.get(0);

        Expression exp = maker.createBinaryOperationExpression(op,list.get(0),list.get(1));
        list.remove(0);
        list.remove(0);
        list.add(0,exp);
        return createBigExpression(list,op);

    }

*/

}


