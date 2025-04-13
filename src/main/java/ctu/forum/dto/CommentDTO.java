package ctu.forum.dto;



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
    public long vote_count;

    public boolean validate() {
        return content != null && !content.isEmpty() && user_id != null && !user_id.isEmpty() && post_id != null && !post_id.isEmpty();
    }
}

