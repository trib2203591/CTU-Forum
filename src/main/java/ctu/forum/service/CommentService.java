package ctu.forum.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import ctu.forum.dto.CommentDTO;
import ctu.forum.interactor.in.ICommentService;
import ctu.forum.interactor.mapper.ICommentMapper;
import ctu.forum.model.Comment;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CommentService implements ICommentService, PanacheMongoRepository<Comment> {

    @Inject
    ICommentMapper commentMapper;

    @Override
    public List<Comment> getCommentByPostId(String post_id) {
        ObjectId id = new ObjectId(post_id);
        List<Comment> comments = this.find("post_id", id).list();
        return comments;
    }

    @Override
    public void createComment(CommentDTO commentDTO) {
        Comment newComment = commentMapper.toComment(commentDTO);
        newComment.created_at = new Date();
        newComment.updated_at = new Date();
        this.persist(newComment);
    }

    @Override
    public void updateComment(CommentDTO commentDTO, String comment_id) {
        ObjectId id = new ObjectId(comment_id);
        Comment commentToUpdate = this.findById(id);
        if (commentToUpdate != null) {
            commentToUpdate.content = commentDTO.content;
            commentToUpdate.updated_at = new Date();
            this.update(commentToUpdate);
        } else {
            throw new IllegalArgumentException("Comment with id " + comment_id + " not found.");
        }
    }

    @Override
    public void deleteComment(String comment_id) {
        ObjectId id = new ObjectId(comment_id);
        Comment commentToDelete = this.findById(id);
        if (commentToDelete != null) {
            this.delete(commentToDelete);
        } else {
            throw new IllegalArgumentException("Comment with id " + comment_id + " not found.");
        }
    }
}
