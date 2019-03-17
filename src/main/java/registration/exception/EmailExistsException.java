package registration.exception;

public class EmailExistsException extends Exception {

    private static final long serialVersionUID = 5676882353683539876L;
    private String message;
    private String code;

    public EmailExistsException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public EmailExistsException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
