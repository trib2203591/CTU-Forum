package ctu.forum.boundary;

import ctu.forum.dto.UserDTO;
import ctu.forum.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("user")
public class UserResource {
    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserDTO userDTO) {
        userService.createUser(userDTO);

        return Response
                    .status(Response.Status.CREATED)
                    .build();
    }
}
