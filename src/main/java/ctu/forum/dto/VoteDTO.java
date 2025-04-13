package ctu.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoteDTO {
    public String post_id;
    public String user_id;
    public String comment_id; 
    public String vote_type;

    public String getVote_type() {
        return vote_type;
    }
    public String getComment_id() {
        return comment_id;
    }
    public String getPost_id() {
        return post_id;
    }
    public String getUser_id() {
        return user_id;
    }

}
