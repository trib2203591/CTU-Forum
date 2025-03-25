package ctu.forum.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post extends PanacheMongoEntity {
    public ObjectId id;
    public String title;
    public String content;
    public List<Tag> tags;
    public ObjectId user_id;
    public Long vote_count;
    public Long comment_count;
    public Date create_at;
    public Date update_at;
}
