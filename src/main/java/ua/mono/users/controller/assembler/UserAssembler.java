package ua.mono.users.controller.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.mono.users.controller.UserController;
import ua.mono.users.controller.model.UserModel;
import ua.mono.users.dto.UserDTO;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserModel> {

    private static final String GET = "get_user";
    private static final String PUT = "update_user";
    private static final String POST = "create_user";

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDTO entity) {
        UserModel userModel = new UserModel(entity);

        Link get = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getByUsername(entity.getUsername())).withRel(GET);
        Link put = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).updateUser(entity.getUsername(), entity)).withRel(PUT);
        Link post = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).createUser(entity)).withRel(POST);

        userModel.add(get, post, put);

        return userModel;
    }

}
