package ctu.forum.dto;

import java.util.List;

import org.bson.types.ObjectId;

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
}
