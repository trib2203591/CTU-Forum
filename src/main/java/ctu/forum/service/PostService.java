package ctu.forum.service;

import java.util.Date;
import java.util.List;


import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;


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
    public List<Post> getPostByText(String string) {
        return find("{ $text : { $search : ?1 } }", string).list();
    }

    @Override
    public Post getPostById(String id) {
        ObjectId postId;
        try {
            postId = new ObjectId(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return findById(postId);
    }

    @Override
    public void createPost(PostDTO postDTO) {
        Post newPost = iPostMapper.toPost(postDTO);
        newPost.create_at = new Date();
        newPost.update_at = new Date();
        this.persist(newPost);
    }

    @Override
    public void updatePost(PostDTO postDTO, String postId) {
        try {
            ObjectId id = new ObjectId(postId);
            Post post = findById(id);
            if (post == null)
                throw new IllegalArgumentException("Post with id " + postId + " not found.");
            if (postDTO.getComment_count() != null) {
                post.setComment_count(postDTO.getComment_count());
            }
            if (postDTO.getVote_count() != null) {
                post.setVote_count(postDTO.getVote_count());
            }
            if (postDTO.getTitle() != null) {
                post.setTitle(postDTO.getTitle());
            }
            if (postDTO.getContent() != null) {
                post.setContent(postDTO.getContent());
            }
            if (postDTO.getUser_id() != null) {
                ObjectId userId = new ObjectId(postDTO.getUser_id());
                post.setUser_id(userId);
            }
            if (postDTO.getTags() != null && !postDTO.getTags().isEmpty()) {
                post.setTags(postDTO.getTags());
            }
            post.update();
        } catch (IllegalArgumentException a) {
            throw new IllegalArgumentException(a.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deletePost(String id) {
        try {
            ObjectId postId = new ObjectId(id);
            Post post = findById(postId);
            if (post == null) {
                throw new IllegalArgumentException("Post with id " + id + " not found.");
            }
            deleteById(postId);
        } catch (IllegalArgumentException a) {
            throw new IllegalArgumentException(a.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
