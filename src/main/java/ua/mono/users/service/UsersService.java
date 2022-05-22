package ua.mono.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.mono.users.dto.UserDTO;
import ua.mono.users.exception.MappingException;
import ua.mono.users.exception.ServiceException;
import ua.mono.users.model.User;
import ua.mono.users.repository.UsersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User user = mapUserDTOToUser(userDTO);
        User savedUser = usersRepository.save(user);
        log.info("New user with username {} created", savedUser.getUsername());
        return mapUserToUserDTO(savedUser);
    }

    public UserDTO updateUser(String username, UserDTO userDTO) {
        User oldUser = usersRepository.findByUsername(username);
        userDTO.setEmail(oldUser.getEmail());
        userDTO.setPassword(oldUser.getPassword());
        userDTO.setRepeatPassword(oldUser.getPassword());
        User user = mapUserDTOToUser(userDTO);
        user = usersRepository.update(username, user);
        log.info("User with username {} is successfully updated", user.getUsername());
        return mapUserToUserDTO(user);
    }

    public List<UserDTO> getAll() {
        List<User> usersList = usersRepository.findAll();
        return usersList.stream().map(this::mapUserToUserDTO).toList();
    }

    public UserDTO getByUsername(String username) {
        User user = usersRepository.findByUsername(username);
        return mapUserToUserDTO(user);
    }

    private User mapUserDTOToUser(UserDTO userDTO) {
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

    private UserDTO mapUserToUserDTO(User user) {
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

}
