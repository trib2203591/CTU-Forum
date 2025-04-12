package ctu.forum.interactor.in;

import java.util.List;

import ctu.forum.dto.PostDTO;
import ctu.forum.model.Post;

public interface IPostService {
    List<Post> getAllPosts();

    List<Post> getPostByText(String string);

    Post getPostById(String id);

    void createPost(PostDTO postDTO);

    void updatePost(PostDTO postDTO, String postId);

    void deletePost(String id);
}
