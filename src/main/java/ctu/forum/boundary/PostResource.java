package ctu.forum.boundary;

import ctu.forum.dto.PostDTO;
import ctu.forum.interactor.in.IPostService;
import ctu.forum.service.PostService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("post")
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
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid data: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unexpected error uccurred."+e.getMessage()).build();
        }
    }

}
