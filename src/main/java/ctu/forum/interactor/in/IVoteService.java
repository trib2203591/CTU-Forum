package ctu.forum.interactor.in;

import java.util.List;

import ctu.forum.dto.VoteDTO;
import ctu.forum.model.Vote;

public interface IVoteService {
    List<Vote> getAllVotes();
    // List<Vote> getVoteByUserId(ObjectId user_id);
    List<Vote> getVoteByPostId(String post_id);
    List<Vote> getVoteByCommentId(String comment_id);
    void createVote(VoteDTO voteDTO);
    void updateVote(String id, VoteDTO voteDTO);
    void deleteVote(String id);
}
