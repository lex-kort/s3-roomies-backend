package nl.fontys.s3.studenthousing.core.exceptions;

public class CustomException extends RuntimeException{
    public CustomException(String errorMessage){
        super(errorMessage);
    }
}
