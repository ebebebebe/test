package test.backend.exception;

public class EmailAlreadyInUsedException extends RuntimeException {
    public EmailAlreadyInUsedException(String message) {
        super(message);
    }
}
