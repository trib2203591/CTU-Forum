package ctu.forum.boundary;

import java.util.Collections;
import java.util.List;

import ctu.forum.dto.PostDTO;
import ctu.forum.interactor.in.IPostService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ctu.forum.model.Post;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.PUT;

@Path("posts")
public class PostResource {
    private final IPostService postService;

    @Inject
    public PostResource(IPostService postService) {
        this.postService = postService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPost(PostDTO postDTO) {
        try {
            postService.createPost(postDTO);
            ResponseMessage responseMessage = new ResponseMessage(true, "Post created successfully.");
            return Response.status(Response.Status.CREATED).entity(responseMessage).build();
        } catch (IllegalArgumentException e) {
            ResponseMessage responseMessage = new ResponseMessage(false, "Invalid data: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(responseMessage).build();
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage(false,
                    "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(responseMessage).build();
        }
    }

    @GET
    // @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            return Response.status(Response.Status.OK).entity(posts).build();
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage(false,
                    "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostById(@PathParam("id") String id) {
        try {
            Post post = postService.getPostById(id);
            if (post == null)
                return Response.status(Response.Status.NOT_FOUND).build();
            return Response.status(Response.Status.OK).entity(post).build();
        } catch (IllegalArgumentException e) {
            ResponseMessage responseMessage = new ResponseMessage(false,
                    "Error: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(responseMessage).build();
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage(false,
                    "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
        }
    }

    @GET
    @Path("search/{string}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostsByText(@PathParam("string") String string) {
        try {
            List<Post> posts = postService.getPostByText(string);
            if (posts == null) {
                posts = Collections.emptyList();
            }
            return Response.ok(posts).build();
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage(false,
                    "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePost(PostDTO post, @PathParam("id") String id) {
        try {
            postService.updatePost(post, id);
            ResponseMessage responseMessage = new ResponseMessage(true, "Post updated successfully.");
            return Response.status(Response.Status.OK).entity(responseMessage).build();
        } catch (IllegalArgumentException e) {
            ResponseMessage responseMessage = new ResponseMessage(false,
                    "Error: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(responseMessage).build();
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage(false,
                    "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePost(@PathParam("id") String string) {
        try {
            postService.deletePost(string);
            ResponseMessage responseMessage = new ResponseMessage(true, "Post deleted successfully.");
            return Response.status(Response.Status.OK).entity(responseMessage).build();
        } catch (IllegalArgumentException e) {
            ResponseMessage responseMessage = new ResponseMessage(false,
                    "Error: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(responseMessage).build();
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage(false,
                    "Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
        }
    }

}
