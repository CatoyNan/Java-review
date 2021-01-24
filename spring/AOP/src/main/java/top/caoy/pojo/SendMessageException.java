package top.caoy.pojo;

public class SendMessageException extends RuntimeException{
    public SendMessageException() {
    }

    public SendMessageException(String message) {
        super(message);
    }

    public SendMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendMessageException(Throwable cause) {
        super(cause);
    }

    public SendMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
