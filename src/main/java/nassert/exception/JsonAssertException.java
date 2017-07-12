package nassert.exception;

/**
 * Created by nguyenho on 7/12/2017.
 */
public class JsonAssertException extends RuntimeException {
    public JsonAssertException(String message) {
        super(message);
    }

    public JsonAssertException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonAssertException(Throwable cause) {
        super(cause);
    }
}