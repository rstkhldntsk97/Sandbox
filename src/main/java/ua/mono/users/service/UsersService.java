package ua.mono.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.mono.users.dto.UserDTO;
import ua.mono.users.exception.UserNotFoundException;
import ua.mono.users.model.User;
import ua.mono.users.repository.UsersRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ClassCanBeRecord")
@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;

    private final MappingService mappingService;

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = mappingService.mapUserDTOToUser(userDTO);
        User savedUser = usersRepository.save(user);
        log.info("New user with username {} created", savedUser.getUsername());
        return mappingService.mapUserToUserDTO(savedUser);
    }

    @Transactional
    public UserDTO putUpdateUser(String username, UserDTO userDTO) {
        User persistedUser = usersRepository.getUserByUsername(username).orElseThrow(UserNotFoundException::new);
        persistedUser.setFirstName(userDTO.getFirstName());
        persistedUser.setLastName(userDTO.getLastName());
        persistedUser.setUsername(userDTO.getUsername());
        log.info("User with username {} is successfully updated", persistedUser.getUsername());
        return mappingService.mapUserToUserDTO(persistedUser);
    }

    @Transactional
    public UserDTO patchUpdateUser(String username, UserDTO userDTO) {
        User persistedUser = usersRepository.getUserByUsername(username).orElseThrow(UserNotFoundException::new);
        mappingService.populateUserWithUserDtoFields(persistedUser, userDTO);
        // this save would not be executed bc of hibernate optimization
        // persistedUser = usersRepository.save(persistedUser);
        log.info("User with username {} is successfully updated", persistedUser.getUsername());
        return mappingService.mapUserToUserDTO(persistedUser);
    }

    @Transactional
    public void deleteUser(String username) {
        User persistedUser = usersRepository.getUserByUsername(username).orElseThrow(() -> new UserNotFoundException("User does not exist"));
        usersRepository.delete(persistedUser);
    }

    public List<UserDTO> getAll() {
        List<User> usersList = usersRepository.findAll();
        return usersList.stream().map(mappingService::mapUserToUserDTO).collect(Collectors.toList());
    }

    public UserDTO getByUsername(String username) {
        User user = usersRepository.getUserByUsername(username).orElseThrow(UserNotFoundException::new);
        return mappingService.mapUserToUserDTO(user);
    }

}
