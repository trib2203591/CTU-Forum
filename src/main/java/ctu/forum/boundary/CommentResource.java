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
    public void createComment(CommentDTO commentDTO) {
        commentService.createComment(commentDTO);
    }

    @PUT
    @Path("/{comment_id}")
    public void updateComment(CommentDTO commentDTO, @PathParam("comment_id") String comment_id) {
        commentService.updateComment(commentDTO, comment_id);
    }

    @DELETE
    @Path("/{comment_id}")
    public void deleteComment(@PathParam("comment_id") String comment_id) {
        commentService.deleteComment(comment_id);
    }
}
