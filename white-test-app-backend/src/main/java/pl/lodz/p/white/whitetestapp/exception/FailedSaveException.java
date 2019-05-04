package pl.lodz.p.white.whitetestapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedSaveException extends AppException {

    public static final String OPERATION_EXECUTION_ERROR_SAVE = "Unable to save query";

    public FailedSaveException() {
    }

    public FailedSaveException(String message) {
        super(message);
    }

    public FailedSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedSaveException(Throwable cause) {
        super(cause);
    }
}
