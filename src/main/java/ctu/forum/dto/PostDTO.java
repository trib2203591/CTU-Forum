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
}
