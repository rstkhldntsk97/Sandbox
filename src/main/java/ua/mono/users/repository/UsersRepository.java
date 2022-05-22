package ua.mono.users.repository;

import ua.mono.users.model.User;

import java.util.List;

public interface UsersRepository {

    User findByUsername(String username);

    User save(User user);

    List<User> findAll();

    User update(String username, User user);

}
