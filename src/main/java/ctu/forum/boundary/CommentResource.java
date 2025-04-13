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
    public Response getCommentByPostId(@PathParam("post_id") String post_id) {
        try {
            List<Comment> comments = commentService.getCommentByPostId(post_id);
            return Response.ok(comments).build();
        } catch (IllegalArgumentException e) {
            ResponseMessage responseMessage = new ResponseMessage(false, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(responseMessage)
                           .build();
        }
        
    }

    @POST
    public Response createComment(CommentDTO commentDTO) {
        try {
            commentService.createComment(commentDTO);
            ResponseMessage responseMessage = new ResponseMessage(true, "Comment created successfully.");
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

    @PUT
    @Path("/{comment_id}")
    public Response updateComment(CommentDTO commentDTO, @PathParam("comment_id") String comment_id) {
        try {
            commentService.updateComment(commentDTO, comment_id);
            ResponseMessage responseMessage = new ResponseMessage(true, "Comment updated successfully.");
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

    @DELETE
    @Path("/{comment_id}")
    public Response deleteComment(@PathParam("comment_id") String comment_id) {
        try {
            commentService.deleteComment(comment_id);
            ResponseMessage responseMessage = new ResponseMessage(true, "Comment deleted successfully.");
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
