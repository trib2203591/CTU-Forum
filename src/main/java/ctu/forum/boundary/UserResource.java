package ctu.forum.boundary;

import java.util.List;

import ctu.forum.dto.SecuredUserDTO;
import ctu.forum.dto.UserDTO;
import ctu.forum.model.User;
import ctu.forum.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService userService;

    @Inject // this is the service injection
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    public Response createUser(UserDTO userDTO) {
        userService.createUser(userDTO);

        return Response
                    .status(Response.Status.CREATED)
                    .build();
    }

    @GET
    @Path("/student_id/{studentId}")
    public Response getUserByStudentId(@PathParam("studentId") String studentId) {
        User user = userService.getUserByStudentId(studentId);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("User not found for student_id: " + studentId)
                           .build();
        }
    }

    @GET
    @Path("/name/{name}")
    public Response findUserByName(@PathParam("name") String name) {
        List<SecuredUserDTO> user = userService.findUserByName(name);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("User not found for name: " + name)
                           .build();
        }
    }

    @DELETE
    @Path("/student_id/{studentId}")
    public Response deleteUser(@PathParam("studentId") String studentId) {
        try {
            userService.deleteUser(studentId);
            return Response.status(Response.Status.OK).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        }
    }
}
