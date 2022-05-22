package ua.mono.users.exception;

import ua.mono.users.model.enums.ErrorType;

public class MappingException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Unexpected error during mapping";

    public MappingException() {
        super(DEFAULT_MESSAGE);
    }

    public MappingException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.PROCESSING_ERROR_TYPE;
    }

}
