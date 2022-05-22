package ua.mono.users.exception;

import ua.mono.users.model.enums.ErrorType;

public class UserAlreadyExistException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Cannot create user";

    public UserAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
