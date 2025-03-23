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
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "create_at", ignore = true)
    @Mapping(target = "update_at", ignore = true)
    @Mapping(target = "vote_count", ignore = true)
    @Mapping(target = "comment_count", ignore = true)
    @Mapping(target = "user_id", source = "user_id")
    Post toPost(PostDTO postDTO);
}