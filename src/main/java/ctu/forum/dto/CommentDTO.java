package ctu.forum.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor  
@AllArgsConstructor 
@Data 
public class CommentDTO {
    public String content;
    public String user_id;
    public String post_id;
    public Date created_at;
    public Date updated_at;
    public long vote_count;
}

