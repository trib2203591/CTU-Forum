package ctu.forum.boundary;

import java.util.List;

import ctu.forum.dto.CommentDTO;
import ctu.forum.model.Comment;
import ctu.forum.service.CommentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("comment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
    final CommentService commentService;

    @Inject
    public CommentResource(CommentService commentService) {
        this.commentService = commentService;
    }

    @GET
    @Path("/post_id/{post_id}")
    public List<Comment> getCommentByPostId(@PathParam("post_id") String post_id) {
        return commentService.getCommentByPostId(post_id);
    }

    @POST
    public Response createComment(CommentDTO commentDTO) {
        commentService.createComment(commentDTO);
        return Response
                .status(Response.Status.OK)
                .entity("{\"success\" : true}")
                .build();
    }

    @PUT
    @Path("/{comment_id}")
    public Response updateComment(CommentDTO commentDTO, @PathParam("comment_id") String comment_id) {
        commentService.updateComment(commentDTO, comment_id);
        return Response
                .status(Response.Status.OK)
                .entity("{\"success\" : true}")
                .build();
    }

    @DELETE
    @Path("/{comment_id}")
    public Response deleteComment(@PathParam("comment_id") String comment_id) {
        commentService.deleteComment(comment_id);
        return Response
                .status(Response.Status.OK)
                .entity("{\"success\" : true}")
                .build();
    }
}
