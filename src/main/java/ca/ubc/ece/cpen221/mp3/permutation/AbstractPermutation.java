package ca.ubc.ece.cpen221.mp3.permutation;

import java.util.List;

/**
 * Abstract Permutation - an interface
 *
 * @param <T>
 *            generic Array
 */
public interface AbstractPermutation<T> {

	// Here T denotes the type of values in the array that we want to permute
	// We are using Java Generics
	// It is enough to use T to denote the type for the permutation task
	// and you can read more about generics here: 
	// https://docs.oracle.com/javase/tutorial/java/generics/index.html

    /**
     * Check if there is another permutation that has not yet been explored
     * @return true if there is a permutation that has not been returned yet,
     *          and false if all permutations have been returned
     */
	boolean hasNext();

    /**
     * Obtain the next permutation to explore,
     * with the precondition that hasNext() returns true
     *
     * @return the next permutation to explore
     */
	List<T> next();

}
