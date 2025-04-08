package exceptions;

public class DuplicateUserIDException extends Exception {
    public DuplicateUserIDException(String message) {
        super(message);
    }

}
