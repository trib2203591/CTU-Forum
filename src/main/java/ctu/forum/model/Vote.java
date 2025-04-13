package ctu.forum.model;
import java.util.Date;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor  //auto create no arg contructor
@AllArgsConstructor //auto create full args constructor
@Data //auto create getters, setters, toString(), equals(), and hashCode()
public class Vote extends PanacheMongoEntity{
    public ObjectId id; 
    public ObjectId post_id;
    public ObjectId user_id;
    public ObjectId comment_id; 
    public String vote_type;
    public Date created_at; // timestamp of when the vote was created
    public Date updated_at; // timestamp of when the vote was last updated

}
