package ua.mono.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.mono.users.model.enums.ErrorType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {

    private String message;
    private ErrorType errorType;
    private LocalDateTime ldt;
}
