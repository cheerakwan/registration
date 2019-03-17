package registration.exception;

public class EmailExistsException extends RegisterException {


    public EmailExistsException(String message, String code) {
        super(message, code);
    }

    public EmailExistsException(String message) {
        super(message);
    }
}
