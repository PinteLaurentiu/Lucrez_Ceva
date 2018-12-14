package lucrez.ceva.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Exception ex) {
        super(ex);
    }
}
