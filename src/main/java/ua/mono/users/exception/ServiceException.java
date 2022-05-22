package ua.mono.users.exception;

import lombok.Data;
import ua.mono.users.model.enums.ErrorType;

// Unified class for all custom exceptions for do not write 100500 handler methods in handler controller
@Data
public abstract class ServiceException extends RuntimeException {

    private ErrorType errorType;

    public ServiceException(String message) {
        super(message);
    }

    public ErrorType getErrorType() {
        return ErrorType.FATAL_ERROR_TYPE;
    }

}
