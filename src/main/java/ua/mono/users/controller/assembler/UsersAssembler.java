package ua.mono.users.controller.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.mono.users.controller.UsersController;
import ua.mono.users.controller.model.UserModel;
import ua.mono.users.dto.UserDTO;

@Component
public class UsersAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserModel> {

    private static final String GET = "get_user";
    private static final String PUT = "PUT_update_user";
    private static final String POST = "create_user";
    private static final String PATCH = "PATCH_update_user";

    public UsersAssembler() {
        super(UsersController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDTO entity) {
        UserModel userModel = new UserModel(entity);

        Link get = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsersController.class).getByUsername(entity.getUsername())).withRel(GET);
        Link put = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsersController.class).putUpdateUser(entity.getUsername(), entity)).withRel(PUT);
        Link post = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsersController.class).createUser(entity)).withRel(POST);
        Link patch = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsersController.class).patchUpdateUser(entity.getUsername(), entity)).withRel(PATCH);

        userModel.add(get, post, put, patch);

        return userModel;
    }

}
