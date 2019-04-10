package ca.ubc.ece.cpen221.mp3.cryptarithm;

public class NoSolutionException extends Exception {
	// You can customize your exception class
	// if you want

    public NoSolutionException(){
    }


    public String getMessage(){
        return "No solution found!";
    }
}
