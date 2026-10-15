package exceptions;

public class InvalidFigureRecordException extends Exception {

    private final int lineNumber;

    public InvalidFigureRecordException(int lineNumber, String message, Throwable cause) {
        super("Строка " + lineNumber + ": " + message, cause);
        this.lineNumber = lineNumber;
    }

    public InvalidFigureRecordException(int lineNumber, String message) {
        this(lineNumber, message, null);
    }

    public int getLineNumber() {
        return lineNumber;
    }
}