package ua.mono.users.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.mono.users.dto.UserDTO;
import ua.mono.users.exception.MappingException;
import ua.mono.users.model.User;
import ua.mono.users.service.MappingService;

import java.lang.reflect.InvocationTargetException;

@Primary
@Service
@Slf4j
@RequiredArgsConstructor
public class MappingServiceWithPUB implements MappingService {

    private final PropertyUtilsBean PUB;

    public User mapUserDTOToUser(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getRepeatPassword())) {
            throw new MappingException("Repeated password does not equal to password");
        }
        try {
            User user = new User();
            PUB.copyProperties(user, userDTO);
            return user;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.info(e.getMessage());
            throw new MappingException();
        }
    }

    public UserDTO mapUserToUserDTO(User user) {
        try {
            UserDTO userDto = new UserDTO();
            PUB.copyProperties(userDto, user);
            return userDto;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.info(e.getMessage());
            throw new MappingException();
        }
    }

    @Override
    public void populateUserWithUserDtoFields(User persistedUser, UserDTO userDTO) {
        if (userDTO.getFirstName() != null) {
            persistedUser.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            persistedUser.setLastName(userDTO.getLastName());
        }
        if (userDTO.getUsername() != null) {
            persistedUser.setUsername(userDTO.getUsername());
        }
    }

}
