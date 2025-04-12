package ctu.forum.model;

import java.util.Date;
import org.bson.types.ObjectId;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;



@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor  
@AllArgsConstructor 
@Data 
public class Comment extends PanacheMongoEntity{
    public ObjectId id; 
    public String content;
    public ObjectId user_id;
    public ObjectId post_id;
    public Date created_at;
    public Date updated_at;
    public long vote_count;
}
