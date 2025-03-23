package ctu.forum.interactor.in;

import java.util.List;

import ctu.forum.dto.PostDTO;
import ctu.forum.model.Post;

public interface IPostService {
    List<Post> getAllPosts();
    void createPost(PostDTO postDTO);
    void updatePost(PostDTO postDTO);
    void deletePost(String id);
}
