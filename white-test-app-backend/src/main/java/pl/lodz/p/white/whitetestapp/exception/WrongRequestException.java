package pl.lodz.p.white.whitetestapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongRequestException extends AppException {

    public static final String EXISTING_DATA_PASSED = "Existing Data Passed";
    public static final String NOT_EXISTING_DATA_REQUESTED = "Request data not exists";
    public static final String ARGUMENT_MISSING = "Request data missing necessary value";
    public static final String NOT_ACCEPTABLE_DATA = "Request data not proper";

    public WrongRequestException() {
    }

    public WrongRequestException(String message) {
        super(message);
    }

    public WrongRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongRequestException(Throwable cause) {
        super(cause);
    }
}
