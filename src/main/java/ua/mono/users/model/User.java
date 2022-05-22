package ua.mono.users.model;

import lombok.*;

@Data
@Builder
public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

}
