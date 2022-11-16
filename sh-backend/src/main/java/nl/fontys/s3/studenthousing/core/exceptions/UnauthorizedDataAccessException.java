package nl.fontys.s3.studenthousing.core.exceptions;

public class UnauthorizedDataAccessException extends CustomException{
    public UnauthorizedDataAccessException(String message){
        super(message);
    }
}
