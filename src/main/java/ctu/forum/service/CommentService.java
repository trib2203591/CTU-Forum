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
        try {
            ObjectId id = new ObjectId(post_id);
            List<Comment> comments = this.find("post_id", id).list();
            if(comments.isEmpty()) {
                throw new IllegalArgumentException("No comments found for post_id " + post_id);
            }
            return comments;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

    }

    @Override
    public void createComment(CommentDTO commentDTO) {
        try {
            if (!commentDTO.validate()) {
                throw new IllegalArgumentException("Invalid comment data.");
            }
            Comment newComment = commentMapper.toComment(commentDTO);
            newComment.created_at = new Date();
            newComment.updated_at = new Date();
            this.persist(newComment);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

    }

    @Override
    public void updateComment(CommentDTO commentDTO, String comment_id) {
        try {
            if (!commentDTO.validate()) {
                throw new IllegalArgumentException("Invalid comment data.");
            }
            ObjectId id = new ObjectId(comment_id);
            Comment commentToUpdate = this.findById(id);
            if (commentToUpdate == null) {
                throw new IllegalArgumentException("Comment with id " + comment_id + " not found.");
            }
            commentToUpdate.content = commentDTO.content;
            commentToUpdate.updated_at = new Date();
            this.update(commentToUpdate);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteComment(String comment_id) {
        try {
            ObjectId id = new ObjectId(comment_id);
            Comment commentToDelete = this.findById(id);
            if (commentToDelete == null) {
                throw new IllegalArgumentException("Comment with id " + comment_id + " not found.");
            } 
            this.delete(commentToDelete);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        
    }
}
