package ca.ubc.ece.cpen221.mp3.permutation;

// add class overview

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutation<T> implements AbstractPermutation<T> {

	private int factorial;  //The max number of permutations possible.
	private List<T> baseList; //The list of all the generic items to permute.
	private int counter;  //Keep count of how many permutations have been performed.
	private ArrayList<Integer> items; //A helper list to keep track of which elements to swap.
	private int element; //The index of the current element to swap.
/*
Rep Invariant:
-The Permutation must not be null

Abstract Function:
Represents one possible permutation of a given list of generic types, order of the list matters
 */
    /**
     * Create a permutation generator with a given list that will be
     * permuted.
	 * Requires: list is not null.
	 * Note: Any mutations to the given baseList may lead to errors in the Permutations.
     * @param baseList List of any type to be permuted.
     */
    public Permutation(List<T> baseList) {
		this.baseList = baseList;
        factorial = factorial(baseList.size());
        counter = 0;
		items = new ArrayList<>();
		element = 0;
    }

	/**
	 * A method which indicates whether another permutation exists for a list, based on the amount of permutations already returned.
	 * @return Boolean, true if another permutation exists that has not been returned yet, and false otherwise.
	 */
	@Override
	public boolean hasNext() {
		if(counter < factorial) return true;
        return false;
	}


	 /**
	 * Obtain the next permutation to explore.
	 * Precondition: hasNext() returns true
	 * Note: Any mutations to the returned list may lead to errors in the permutations.
	 * @return The next permutation to explore.
	 */
	@Override
	public List<T> next() {

		if (counter == 0) { //Only for the first iteration of the permutations
			for (int i = 0; i < baseList.size(); i++) items.add(i);  //initialize ArrayList
			counter++; //to return the original sequence as the first permutation
			return baseList;
		}

		while(items.get(element) == baseList.size()-1){  //The element that is being swapped has reached the end of the list.
			originalOrder(element);
			items.set(element,element);
			element++;
		}

		swap(items.get(element));  //Swap and Return the next permutation.
		items.set(element, items.get(element) + 1);
		if(element!=0) element=0;
		counter++;
		return baseList;
	}

	/**
	 * A method to calculate the factorial of a number
	 * Requires: f > 0
	 * @param f The number to obtain the factorial of
	 * @return an integer of the factorial
	 */
	private int factorial(int f) {
		int fact = 1;
		for (int i = 1; i < f + 1; i++) {
			fact *= i;
		}
		return fact;
	}

	/**
	 * A method to swap a generic element at index x, with the element at index x+1.
	 * Does nothing if x is not less than the size of the list to permute.
	 * Requires: x > 0
	 * @param x the index of the element to swap.
	 */
	private void swap(int x) {

		if (x < baseList.size()) {

			T temp = baseList.get(x);
			baseList.set(x,baseList.get(x+1));
			baseList.set(x+1,temp);

		}
	}


	/**
	 * A method to return the list back to its original order
	 * Requires: n is an index of the baseList
	 * @param n The position where the last element of the list is returned to.
	 */
	private void originalOrder(int n) {

		int size = baseList.size();
		T temp = baseList.get(size - 1);

		for (int i = size - 1; i > n; i--) {
			baseList.set(i,baseList.get(i-1));
		}
		baseList.set(n,temp);
	}


}








