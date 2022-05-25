package ua.mono.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.mono.users.dto.group.OnCreate;
import ua.mono.users.dto.group.OnUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @NotBlank(message = "First name shouldn't be empty", groups = OnCreate.class)
    private String firstName;

    @NotBlank(message = "Last name shouldn't be empty", groups = OnCreate.class)
    private String lastName;

    @NotBlank(message = "Username shouldn't be empty", groups = OnCreate.class)
    private String username;

    @Email
    @Null(message = "Email should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "Email shouldn't be empty", groups = OnCreate.class)
    private String email;

    @Null(message = "Password should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "Password shouldn't be empty", groups = OnCreate.class)
    @Size(message = "Minimum length is 8 characters", min = 8, max = 25)
    private String password;

    @Null(message = "Must be equal to password", groups = OnUpdate.class)
    @NotBlank(message = "Repeat password is required", groups = OnCreate.class)
    private String repeatPassword;

    @Null(message = "Field should be absent in request", groups = OnCreate.class)
    @Null(message = "Field should be absent in request", groups = OnUpdate.class)
    private LocalDateTime createdAt;

    @Null(message = "Field should be absent in request", groups = OnCreate.class)
    @Null(message = "Field should be absent in request", groups = OnUpdate.class)
    private LocalDateTime updatedAt;

}
