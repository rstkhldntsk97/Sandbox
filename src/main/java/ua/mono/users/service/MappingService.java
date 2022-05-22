package ua.mono.users.service;

import ua.mono.users.dto.UserDTO;
import ua.mono.users.model.User;

public interface MappingService {

    User mapUserDTOToUser(UserDTO userDTO);

    UserDTO mapUserToUserDTO(User user);

    void populateUserWithUserDtoFields(User persistedUser, UserDTO userDTO);

}
