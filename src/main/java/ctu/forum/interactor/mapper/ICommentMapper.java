package ctu.forum.interactor.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ctu.forum.dto.CommentDTO;
import ctu.forum.model.Comment;

@Mapper(componentModel = "cdi")
public interface ICommentMapper {

    default ObjectId map(String value) {
        return value == null ? null : new ObjectId(value);
    }

    @Mapping(target="id", ignore = true)
    @Mapping(target="created_at", ignore = true)
    @Mapping(target="updated_at", ignore = true)
    @Mapping(source = "user_id", target = "user_id") 
    @Mapping(source = "post_id", target = "post_id")
    Comment toComment(CommentDTO commentDTO);

}
