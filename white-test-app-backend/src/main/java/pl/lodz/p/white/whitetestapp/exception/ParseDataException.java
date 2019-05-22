package pl.lodz.p.white.whitetestapp.exception;

public class ParseDataException extends AppException {

    public static final String COLUMN_ERROR_FORMAT_NUMERIC = "Error in column %d. Field must be numeric.";
    public static final String COLUMN_ERROR_EMPTY = "Error in column %d. Field cannot be empty.";
    public static final String COLUMN_ERROR_WRONG_VALUE = "Error in column 5. Field not numeric or pipe.";
    public static final String COLUMN_ERROR_QUESTION_TYPE = "Error in question type column. Question type not acceptable";
    public static final String COLUMN_ERROR_LANGUAGE_TYPE = "Error in language column. Language value not acceptable";
    public static final String COLUMN_ERROR_ANSWERS_COUNT = "Number of answer options must be at least one.";

    public ParseDataException() {
        super();
    }

    public ParseDataException(String message) {
        super(message);
    }

    public ParseDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseDataException(Throwable cause) {
        super(cause);
    }
}
