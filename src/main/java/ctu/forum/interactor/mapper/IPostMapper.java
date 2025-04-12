package ctu.forum.interactor.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ctu.forum.dto.PostDTO;
import ctu.forum.model.Post;

@Mapper(componentModel = "cdi")
public interface IPostMapper {
    
    default ObjectId map(String value) {
        return value == null ? null : new ObjectId(value);
    }
    // default BigInteger map(Integer value) {
    //     return value == null ? BigInteger.ZERO : BigInteger.valueOf(value);
    // }
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "create_at", ignore = true)
    @Mapping(target = "update_at", ignore = true)
    @Mapping(target = "user_id", source = "user_id")
    @Mapping(target = "vote_count", source = "vote_count")
    @Mapping(target = "comment_count", source = "comment_count")
    Post toPost(PostDTO postDTO);
}