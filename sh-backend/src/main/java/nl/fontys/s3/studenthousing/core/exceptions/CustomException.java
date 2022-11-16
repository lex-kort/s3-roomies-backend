package nl.fontys.s3.studenthousing.core.exceptions;

public class CustomException extends Exception {
    private final String message;
    public CustomException(String message){
        this.message = message;
    }
}
