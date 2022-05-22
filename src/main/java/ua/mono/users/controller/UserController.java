package ua.mono.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ua.mono.users.api.UserApi;
import ua.mono.users.controller.assembler.UserAssembler;
import ua.mono.users.controller.model.UserModel;
import ua.mono.users.dto.UserDTO;
import ua.mono.users.service.UsersService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UsersService usersService;

    private final UserAssembler userAssembler;

    @Override
    public ResponseEntity<UserModel> createUser(UserDTO userDto) {
        UserDTO user = usersService.createUser(userDto);
        return new ResponseEntity<>(userAssembler.toModel(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserModel> putUpdateUser(String username, UserDTO userDto) {
        UserDTO user = usersService.putUpdateUser(username, userDto);
        return new ResponseEntity<>(userAssembler.toModel(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserModel> patchUpdateUser(String username, UserDTO userDto) {
        UserDTO user = usersService.patchUpdateUser(username, userDto);
        return new ResponseEntity<>(userAssembler.toModel(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserModel> getByUsername(String username) {
        UserDTO user = usersService.getByUsername(username);
        return new ResponseEntity<>(userAssembler.toModel(user), HttpStatus.OK);
    }

    @Override
    public List<UserDTO> getAll() {
        return usersService.getAll();
    }

}
