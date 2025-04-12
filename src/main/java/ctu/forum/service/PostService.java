package ctu.forum.service;

import java.util.Date;
import java.util.List;

import ctu.forum.dto.PostDTO;
import ctu.forum.interactor.in.IPostService;
import ctu.forum.interactor.mapper.IPostMapper;
import ctu.forum.model.Post;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PostService implements IPostService, PanacheMongoRepository<Post> {
    @Inject 
    IPostMapper iPostMapper;

    @Override
    public List<Post> getAllPosts() {
        return listAll();
    }

    @Override
    public void createPost(PostDTO postDTO) {
        Post newPost = iPostMapper.toPost(postDTO);
        newPost.create_at = new Date();
        newPost.update_at = new Date();
        this.persist(newPost);
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePost'");
    }

    @Override
    public void deletePost(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

}
