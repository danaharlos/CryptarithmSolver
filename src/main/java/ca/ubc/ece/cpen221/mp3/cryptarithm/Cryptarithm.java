package ca.ubc.ece.cpen221.mp3.cryptarithm;

import ca.ubc.ece.cpen221.mp3.expression.BinaryOperatorExpression;
import ca.ubc.ece.cpen221.mp3.expression.Expression;
import ca.ubc.ece.cpen221.mp3.expression.NumberExpression;
import ca.ubc.ece.cpen221.mp3.expression.VariableExpression;
import ca.ubc.ece.cpen221.mp3.operator.*;
import ca.ubc.ece.cpen221.mp3.parser.ExpressionMaker;
import ca.ubc.ece.cpen221.mp3.parser.ExpressionParser;
import ca.ubc.ece.cpen221.mp3.parser.OrderOfOperationsRules;
import ca.ubc.ece.cpen221.mp3.permutation.Permutation;

import java.util.*;

/**
 * Cryptarithm - a datatype that represents a cryptarithm
 *
 */
public class Cryptarithm {
	private String message;
	//"Invalid Cryptarithm! A cryptarithm must resemble an arithmetic equation. \nExample: SEND + MORE = MONEY"
	private String[] cryptarithm;
	private List<VariableExpression> allVarExpList; //set of all variable used in cryptarithm
	private List<NumberExpression> tens;
	private ExpressionMaker maker;
    private List<Character> firstLettersOfWord; //All the first letters of words to check if they are assigned a 0 in the solution.
	private Expression left;
	private Expression right;
/*
Rep Invariant:
-Cryptarithm must not be null

Abstract Function:
Represents a cryptarithm which could have 0, 1, or more solutions
 */
	/**
	 * Cryptarithm constructor
	 * 
	 * @param cryptarithm
	 *            where each element is a String that represents part of the
	 *            cryptarithm
	 */
	public Cryptarithm(String[] cryptarithm) throws InvalidCryptarithmException{
		this.cryptarithm = cryptarithm;
		allVarExpList = new ArrayList<>(); //set of all variable used in cryptarithm
		tens = new ArrayList<>();
		maker = new ExpressionMaker();
        firstLettersOfWord = new ArrayList<>();
        if(!isValid()) throw new InvalidCryptarithmException(message); //check validity
		init(); //if valid, initialize crypto

	}

	/**
	 * Find solutions to the cryptarithm
	 * 
	 * @return a list of all possible solutions to the given cryptarithm. A
	 *         solution is a map that provides the value for each alphabet in
	 *         the cryptarithm.
	 */
	public List<Map<Character, Integer>> solve() throws NoSolutionException {
		List<Map<Character, Integer>> solutions  = new ArrayList<>();
		List<Integer> originalIndices = new ArrayList<>();
		Collections.addAll(originalIndices, 0,1,2,3,4,5,6,7,8,9); //all possible digits
		Permutation<Integer> perm = new Permutation<>(originalIndices);

		while(perm.hasNext()){

			List<Integer> indices = perm.next();

			for(int i = 0; i < allVarExpList.size(); i++){
					allVarExpList.get(i).store(indices.get(i));
				}

			if(right.eval() == left.eval()){    //A solution has been found.

                    Map<Character,Integer> solutionMap = new HashMap<>();
                    for (int j = 0; j < allVarExpList.size(); j++) {
                        Character name = allVarExpList.get(j).name().charAt(0);
                        Integer value = indices.get(j);
                        solutionMap.put(name, value);
                    }

                boolean valid = true;

                for(Character firstLetter : firstLettersOfWord){   //Check if any of the first letters of words are 0.
                    if(solutionMap.get(firstLetter) == 0) valid = false;
                }

                if(!solutions.contains(solutionMap) && valid) solutions.add(solutionMap); //Only add solution if it does not already exists
                                                                                          //And all the first letters of words start with a digit > 0
			}

		}

		if(solutions.size() == 0) throw new NoSolutionException(); //if no solutions, throw exception
		return solutions;

	}


	/**
	 *Initializes the cryptarithm by converting it from a string array to two expressions, right and left
	 */
	private void init(){
		BinaryOperator add = new AdditionOperator();
		BinaryOperator sub = new SubtractionOperator();
		BinaryOperator mul = new MultiplicationOperator();
		BinaryOperator div = new DivisionOperator();
		List<Expression> individualWordExpsLS = new ArrayList<>();
		List<BinaryOperator> opsLS = new ArrayList<>();
		List<Expression> individualWordExpsRS = new ArrayList<>();
		List<BinaryOperator> opsRS = new ArrayList<>();

		makeTens();
		boolean seenEquals=false; //is true when parsing through right side of equation

	for(String word : cryptarithm){ //goes through each string and checks if it is an operator, if not assumes it is variable expression
		if(word.equals("=")){
			seenEquals=true;
		}else if(word.equals("+")){
			if(seenEquals)
				opsRS.add(add);
			else
				opsLS.add(add);
		}else if(word.equals("-")) {
			if(seenEquals)
				opsRS.add(sub);
			else
				opsLS.add(sub);
		}else if(word.equals("*")){
			if(seenEquals)
				opsRS.add(mul);
			else
				opsLS.add(mul);
		}else if(word.equals("/")){
			if(seenEquals)
				opsRS.add(div);
			else
				opsLS.add(div);
		}else{

		    firstLettersOfWord.add(word.charAt(0)); //keeps track of all first letters of words, these can't be zero

			List<VariableExpression> varExpList = new ArrayList<>();
			//Creating a list of variableExpressions whose names are the individual letters.
			boolean alreadyExists=false;
			for(int i = 0; i < word.length(); i++){
				for(VariableExpression ve: allVarExpList) {
					if (Character.toString(word.charAt(i)).equals(ve.name())) {
						alreadyExists=true;
						varExpList.add(ve);
					}
				}
				if(!alreadyExists){
					VariableExpression nve = new VariableExpression(word.substring(i, i + 1));
					varExpList.add(nve);
					allVarExpList.add(nve);
				}
				alreadyExists=false;
			}

			List<Expression> tensVariableList = new ArrayList<>();

			int count = varExpList.size()-1; //multiplies each letter of cryptarithm by digit significance it corresponds to
			for(int j = 0; j <  varExpList.size(); j++){
				Expression boe = maker.createBinaryOperationExpression(mul,varExpList.get(j),tens.get(count));
				tensVariableList.add(boe);
				count--;
			}

			if(seenEquals) individualWordExpsRS.add(createBigExpression(tensVariableList,add));
			else individualWordExpsLS.add(createBigExpression(tensVariableList,add));

			varExpList.clear();
			tensVariableList.clear();
		}
	}

	left = createFullExpression(individualWordExpsLS,opsLS);
	right = createFullExpression(individualWordExpsRS,opsRS);

	}

	/**
	 * Create a full expression out of an ordered list of operators and expressions
	 * @param list Expressions to combine.
	 * @param ops Operators.
	 * Requires: ops.size() - list.size - 1
	 * @return A full expression that encompass the list and operators together.
	 */
	private Expression createFullExpression(List<Expression> list, List<BinaryOperator> ops){

		if(list.size() == 0) return new VariableExpression("BLANK"); //Only return an arbitrary expression because the expression is invalid.

		int sizeOps=ops.size();
		for(int i=0; i<sizeOps;i++) { //makes expression representing entire Cryptarithm

			Expression exp = maker.createBinaryOperationExpression(ops.get(0), list.get(0), list.get(1));
			list.remove(0);
			list.remove(0);
			list.add(0,exp);
			ops.remove(0);
		}

		return list.get(0);
	}


	/**
	 * Creates a summed expression of a list of expressions
	 * @param list is not null.
	 * @param op The operator.
	 * @return One big expression of each expression summed together.
	 */
	private Expression createBigExpression(List<Expression> list,BinaryOperator op){

		if(list.size()==1) return list.get(0);

		Expression exp = maker.createBinaryOperationExpression(op,list.get(0),list.get(1));
		list.remove(0);
		list.remove(0);
		list.add(0,exp);
		return createBigExpression(list,op);

	}

	/**
	 * Fill the tens list with the appropriate amount of powers of 10.
	 * Requires nothing.
	 */
	private void makeTens(){
		int big = 0;
		for(String word : cryptarithm){
			if(word.length() > big) big = word.length();
		}

		int multiplier = 1;
		//Make the List
		for(int i = 0; i < big; i++){
			NumberExpression ne = new NumberExpression(multiplier);
			tens.add(ne);
			multiplier *= 10;
		}
	}

	/**Checks whether or not a cryptarithm is considered valid, ie it is composed of a sequence that start with
	 * a string of alphabetic characters, followed by an operator (one of +,-,*,/,=), and alternating int hat way
	 * until it ends with a string.
	 * A valid cryptarithm needs an "="
	 * If more than 10 letters that represent variables are found, the cryptarithm is not valid
	 * Requires nothing.
	 * @return boolean which expresses the validity of the cryparithm
	 */
	private boolean isValid(){

		boolean seenEquals=false;
		boolean isValid=true;

		//Find all unique letters
		Set<Character> letters = new HashSet<>();
		for(String word : cryptarithm){
			if(!word.equals("+")  && !word.equals("-") &&!word.equals("*")  && !word.equals("/")  && !word.equals("=") ) {
				for (int j = 0; j < word.length(); j++) {
					letters.add(word.charAt(j));
				}
			}
		}

		//checks that there are no more than 10 variables to be assigned to a digit
		if(letters.size() > 10) {

			message = "Too many unique letters! ";
			return false;
		}
		for(int i=0;i<cryptarithm.length;i++){
			if(i%2==1) {//checks that every other string in cryptarithm is an operator
				if(!cryptarithm[i].equals("+")&&!cryptarithm[i].equals("-")&&!cryptarithm[i].equals("*")
						&& !cryptarithm[i].equals("/")&&!cryptarithm[i].equals("=")) {
					message = "There needs to be an operator between each word! ";
					//return false;
					isValid=false;

				}

				if(cryptarithm[i].equals("=")&&!seenEquals)
					seenEquals=true;
				else if(cryptarithm[i].equals("=")&&seenEquals) {
					message = "You can only have one '=' sign! ";
					//return false;
					isValid=false;

				}

			}
			if(i%2==0) {//checks that the rest of strings are not operators
				if(cryptarithm[i].equals("+")||cryptarithm[i].equals("-")||cryptarithm[i].equals("*")
						|| cryptarithm[i].equals("/")||cryptarithm[i].equals("=")) {
					message = "You cannot have two operators side by side! ";
					//return false;
					isValid=false;
				}
			}
		}
		if(!seenEquals) { //makes sure there is an "=" in the cryptarhim
			message = "You do not have an '=' sign! ";
			//return false;
			isValid=false;
		}
		//checks that all variable expressions are valid alphabetic characters
		for(Character letter: letters){
			if(!Character.isAlphabetic(letter)) {
				message = "Invalid symbol: " + letter;
				//return false;
				isValid=false;
			}
		}

		return isValid;
		//message = "VALID";

	}


}
