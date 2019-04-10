package ca.ubc.ece.cpen221.mp3.cryptarithm;

public class InvalidCryptarithmException extends Exception {
    private String message;
    public InvalidCryptarithmException(String message){
       this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
