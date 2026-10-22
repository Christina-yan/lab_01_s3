package exceptions;

public class InvalidFigureParameterException extends RuntimeException{

    public InvalidFigureParameterException(String message){
        super(message);
    }

    public InvalidFigureParameterException(String message, Throwable cause){
        super(message, cause);
    }
}
