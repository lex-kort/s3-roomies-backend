package nl.fontys.s3.studenthousing.core.exceptions;

public class EmailNotFoundException extends CustomException{
    public EmailNotFoundException(){
        super("EMAIL_NOT_FOUND");
    }
}
