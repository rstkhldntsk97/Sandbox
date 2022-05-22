package ua.mono.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.mono.users.api.UserApi;
import ua.mono.users.controller.assembler.UserAssembler;
import ua.mono.users.controller.model.UserModel;
import ua.mono.users.dto.UserDTO;
import ua.mono.users.dto.group.OnCreate;
import ua.mono.users.dto.group.OnUpdate;
import ua.mono.users.service.UsersService;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/v1/users")
public class UserController implements UserApi {

    private final UsersService usersService;

    private final UserAssembler userAssembler;

    //    @PostMapping("/create")
    public ResponseEntity<UserModel> createUser(UserDTO userDto) {
        UserDTO user = usersService.createUser(userDto);
        return new ResponseEntity<>(userAssembler.toModel(user), HttpStatus.CREATED);
    }

    //    @PutMapping("update/{username}")
    public ResponseEntity<UserModel> updateUser(String username, UserDTO userDto) {
        UserDTO user = usersService.updateUser(username, userDto);
        return new ResponseEntity<>(userAssembler.toModel(user), HttpStatus.OK);
    }

    //    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<UserModel> getByUsername(String username) {
        UserDTO user = usersService.getByUsername(username);
        return new ResponseEntity<>(userAssembler.toModel(user), HttpStatus.OK);
    }

    //    @GetMapping
    public List<UserDTO> getAll() {
        return usersService.getAll();
    }

}
