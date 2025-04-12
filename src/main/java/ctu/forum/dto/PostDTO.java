package ctu.forum.dto;

import java.util.List;

import ctu.forum.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PostDTO {
    private String user_id;
    private String title;
    private String content;
    private List<Tag> tags;
    private Long vote_count;
    private Long comment_count;

    public String getUser_id() {
        return this.user_id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public Long getVote_count() {
        return this.vote_count;
    }

    public Long getComment_count() {
        return this.comment_count;
    }
}
