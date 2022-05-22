package ua.mono.users.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.mono.users.controller.model.UserModel;
import ua.mono.users.dto.UserDTO;
import ua.mono.users.dto.group.OnCreate;
import ua.mono.users.dto.group.OnUpdate;

import java.util.List;

@Api(tags = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {


    @PostMapping("/create")
    @ApiOperation("create user")
    ResponseEntity<UserModel> createUser(@RequestBody @Validated(OnCreate.class) UserDTO userDto);

    @PutMapping("update/{username}")
    @ApiOperation("update user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", paramType = "path", required = true, value = "Username")
    })
    ResponseEntity<UserModel> updateUser(@PathVariable String username, @RequestBody @Validated(OnUpdate.class) UserDTO userDto);

    @GetMapping("/getByUsername/{username}")
    @ApiOperation("get user by it's username")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", paramType = "path", required = true, value = "Username")
    })
    ResponseEntity<UserModel> getByUsername(@PathVariable String username);

    @GetMapping
    @ApiOperation("get list of all users")
    List<UserDTO> getAll();

}
