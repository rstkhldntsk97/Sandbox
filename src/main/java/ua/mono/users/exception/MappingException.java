package ua.mono.users.exception;

import ua.mono.users.model.enums.ErrorType;

public class MappingException extends ServiceException {

    public MappingException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.PROCESSING_ERROR_TYPE;
    }

}
