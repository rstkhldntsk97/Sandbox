package ua.mono.users.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.mono.users.controller.model.UserModel;
import ua.mono.users.dto.UserDTO;
import ua.mono.users.dto.group.OnCreate;
import ua.mono.users.dto.group.OnUpdate;

import java.util.List;

@Tag(name = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {

    @PostMapping("/create")
    @Operation(description = "create user")
    ResponseEntity<UserModel> createUser(@RequestBody @Validated(OnCreate.class) UserDTO userDto);

    @PutMapping("update/{username}")
    @Operation(description = "update user")
    @Parameters({
            @Parameter(name = "username", required = true, description = "Username")
    })
    ResponseEntity<UserModel> updateUser(@PathVariable String username, @RequestBody @Validated(OnUpdate.class) UserDTO userDto);

    @GetMapping("/getByUsername/{username}")
    @Operation(description = "get user by it's username")
    @Parameters({
            @Parameter(name = "username", ref = "path", required = true, description = "Username")
    })
    ResponseEntity<UserModel> getByUsername(@PathVariable String username);

    @GetMapping
    @Operation(description = "get list of all users")
    List<UserDTO> getAll();

}
