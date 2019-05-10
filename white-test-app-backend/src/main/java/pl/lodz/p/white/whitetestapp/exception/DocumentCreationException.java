package pl.lodz.p.white.whitetestapp.exception;

public class DocumentCreationException extends AppException {

    public static final String ERROR_CREATING_DOCUMENT = "Document couldn't be created ";

    public DocumentCreationException() {
        super();
    }

    public DocumentCreationException(String message) {
        super(message);
    }

    public DocumentCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentCreationException(Throwable cause) {
        super(cause);
    }
}
