package pl.lodz.p.white.whitetestapp.exception;

public class ScreenshotFileSaveException extends AppException {
    public ScreenshotFileSaveException() {
        super();
    }

    public ScreenshotFileSaveException(String message) {
        super(message);
    }

    public ScreenshotFileSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScreenshotFileSaveException(Throwable cause) {
        super(cause);
    }
}
