package nl.fontys.s3.studenthousing.core.exceptions;

public class EmptyInputException  extends CustomException{
    public EmptyInputException(){
        super("INPUT_IS_EMPTY");
    }
}
