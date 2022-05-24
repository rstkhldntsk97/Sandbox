package ua.mono.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.mono.users.api.UsersApi;
import ua.mono.users.controller.assembler.UsersAssembler;
import ua.mono.users.controller.model.UserModel;
import ua.mono.users.dto.UserDTO;
import ua.mono.users.model.User;
import ua.mono.users.repository.UsersRepository;
import ua.mono.users.service.UsersService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {

    private final UsersService usersService;
    private final UsersRepository repository;

    private final UsersAssembler usersAssembler;

    @Override
    public ResponseEntity<UserModel> createUser(UserDTO userDto) {
        UserDTO user = usersService.createUser(userDto);
        return new ResponseEntity<>(usersAssembler.toModel(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        User save = repository.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/api/v1/users/getByUsername/{username}")
                .buildAndExpand(user.getUsername())
                .toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @Override
    public ResponseEntity<UserModel> putUpdateUser(String username, UserDTO userDto) {
        UserDTO user = usersService.putUpdateUser(username, userDto);
        return new ResponseEntity<>(usersAssembler.toModel(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserModel> patchUpdateUser(String username, UserDTO userDto) {
        UserDTO user = usersService.patchUpdateUser(username, userDto);
        return new ResponseEntity<>(usersAssembler.toModel(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String username) {
        usersService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserModel> getByUsername(String username) {
        UserDTO user = usersService.getByUsername(username);
        return new ResponseEntity<>(usersAssembler.toModel(user), HttpStatus.OK);
    }

    @Override
    public List<UserDTO> getAll() {
        return usersService.getAll();
    }

}
