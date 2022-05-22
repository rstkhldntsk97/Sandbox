package ua.mono.users.service.impl;

import org.springframework.stereotype.Service;
import ua.mono.users.dto.UserDTO;
import ua.mono.users.exception.MappingException;
import ua.mono.users.model.User;
import ua.mono.users.service.MappingService;

@Service
public class MyMappingService implements MappingService {

    public User mapUserDTOToUser(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getRepeatPassword())) {
            throw new MappingException("Repeated password does not equal to password");
        }
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }

    public UserDTO mapUserToUserDTO(User user) {
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Override
    public void populateUserWithUserDtoFields(User persistedUser, UserDTO userDTO) {

    }

}
