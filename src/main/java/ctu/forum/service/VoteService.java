package ctu.forum.service;

import java.util.List;

import org.bson.types.ObjectId;

import ctu.forum.dto.VoteDTO;
import ctu.forum.interactor.in.IVoteService;
import ctu.forum.interactor.mapper.IVoteMapper;
import ctu.forum.model.Vote;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VoteService implements IVoteService, PanacheMongoRepository<Vote> {
    
    @Inject
    IVoteMapper voteMapper;

    @Override
    public List<Vote> getAllVotes() {
        List<Vote> allVotes = findAll().list();
        return allVotes;
    }
    @Override
    public List<Vote> getVoteByPostId(String post_id) {
        try{
            List<Vote> votesByPostId = find("post_id", new ObjectId(post_id)).list();
            return votesByPostId;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid post_id: " + post_id, e);
        }

    }
    @Override
    public List<Vote> getVoteByCommentId(String comment_id) {
        try {
            List<Vote> votesByCommentId = find("comment_id", new ObjectId(comment_id)).list();
            return votesByCommentId;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid comment_id: " + comment_id, e);
        }
    }

    @Override
    public void createVote(VoteDTO voteDTO) {
        // Validate vote_type
        if (!List.of("love", "like", "sad", "haha", "wow", "angry").contains(voteDTO.getVote_type())) {
            throw new IllegalArgumentException("Invalid vote_type: " + voteDTO.getVote_type());
        }

        Vote newVote = voteMapper.toVote(voteDTO);
        newVote.created_at = newVote.updated_at = new java.util.Date(); 
        newVote.persist(); 
    }

    @Override
    public void updateVote(String id, VoteDTO voteDTO) {
        Vote vote = findById(new ObjectId(id));

        if(vote == null) {
            throw new IllegalArgumentException("Vote not found with id: " + id);
        }

        voteMapper.updateVoteFromDTO(voteDTO, vote);
        vote.updated_at = new java.util.Date();

        vote.persistOrUpdate(); 
    }

    @Override
    public void deleteVote(String id) {
        Vote vote = findById(new ObjectId(id));

        if(vote == null) {
            throw new IllegalArgumentException("Vote not found with id: " + id);
        }

        vote.delete();
    }
    
}
