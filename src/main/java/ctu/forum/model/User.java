package ctu.forum.model;

import java.util.Date;
import org.bson.types.ObjectId;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;



@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor  //auto create no arg contructor
@AllArgsConstructor //auto create full args constructor
@Data //auto create getters, setters, toString(), equals(), and hashCode()
public class User extends PanacheMongoEntity{
    public ObjectId id; // used by MongoDB for the _id field
    public String student_id;
    public String name;
    public String email;
    public String password_hash;
    public Date created_at;
    public Date updated_at;
}
