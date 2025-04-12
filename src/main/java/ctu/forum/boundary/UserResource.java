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
        try {
            ResponseMessage responseMessage = new ResponseMessage(true, "User created successfully.");
            userService.createUser(userDTO);
            return Response
                    .status(Response.Status.OK)
                    .entity(responseMessage)
                    .build();
        } catch (IllegalArgumentException e) {
            ResponseMessage responseMessage = new ResponseMessage(false, "Error: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                            .entity(responseMessage)
                            .build();
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage(false, "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(responseMessage)
                            .build();
        }
    }

    @GET
    @Path("/student_id/{studentId}")
    public Response getUserByStudentId(@PathParam("studentId") String studentId) {
        try {
            User user = userService.getUserByStudentId(studentId);
            return Response.ok(user).build();

        } catch (IllegalArgumentException e) {
            ResponseMessage responseMessage = new ResponseMessage(false, "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(responseMessage)
                            .build();
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage(false, "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(responseMessage)
                            .build();
        }

    }

    @GET
    @Path("/name/{name}")
    public Response findUserByName(@PathParam("name") String name) {
        try {
            List<SecuredUserDTO> user = userService.findUserByName(name);
            return Response.ok(user).build();
        } catch (IllegalArgumentException e) {
            ResponseMessage responseMessage = new ResponseMessage(false, "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(responseMessage)
                            .build();
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage(false, "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(responseMessage)
                            .build();
        }


    }

    @DELETE
    @Path("/student_id/{studentId}")
    public Response deleteUser(@PathParam("studentId") String studentId) {
        try {
            userService.deleteUser(studentId);
            ResponseMessage responseMessage = new ResponseMessage(true, "User deleted successfully.");
            return Response.status(Response.Status.OK)
                            .entity(responseMessage)
                            .build();
        } catch (IllegalArgumentException e) {
            ResponseMessage responseMessage = new ResponseMessage(false, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(responseMessage)
                           .build();
        }
    }
}
