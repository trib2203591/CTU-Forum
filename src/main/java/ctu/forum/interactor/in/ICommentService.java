package ctu.forum.interactor.in;

import java.util.List;

import ctu.forum.dto.CommentDTO;
import ctu.forum.model.Comment;


public interface ICommentService {
    List<Comment> getCommentByPostId(String post_id);
    void createComment(CommentDTO commentDTO);
    void updateComment(CommentDTO commentDTO, String comment_id);
    void deleteComment(String comment_id);
}
