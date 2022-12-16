package nl.fontys.s3.studenthousing.core.exceptions;

public class EmailAlreadyTakenException extends CustomException{
    public EmailAlreadyTakenException(){
        super("EMAIL_IN_USE");
    }
}
