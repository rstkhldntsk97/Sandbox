package ua.mono.users.repository.impl;

import org.springframework.stereotype.Component;
import ua.mono.users.exception.UserAlreadyExistException;
import ua.mono.users.exception.UserNotFoundException;
import ua.mono.users.model.User;
import ua.mono.users.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {

    private List<User> repository = new ArrayList<>();

    @Override
    public User findByUsername(String username) {
        return repository.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User save(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        Optional<User> usernameExist = repository.stream().filter(u -> u.getUsername().equals(username)).findAny();
        Optional<User> emailExist = repository.stream().filter(u -> u.getEmail().equals(email)).findAny();
        if (usernameExist.isPresent() || emailExist.isPresent()) {
            throw new UserAlreadyExistException();
        }
        repository.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return repository;
    }

    @Override
    public User update(String username, User user) {
        User persistUser = repository.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("You can not update user which is not exist"));
        repository.remove(persistUser);
        repository.add(user);
        return user;
    }

}
