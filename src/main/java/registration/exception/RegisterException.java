package registration.exception;

public class RegisterException extends Exception
{
    private static final long serialVersionUID = 5676882353683539876L;
    private String message;
    private String code;

    public RegisterException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public RegisterException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
