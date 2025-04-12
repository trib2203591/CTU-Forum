package ctu.forum.interactor.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ctu.forum.dto.VoteDTO;
import ctu.forum.model.Vote;

@Mapper(componentModel = "cdi")
public interface IVoteMapper {

    default ObjectId map(String value) {
        return value == null ? null : new ObjectId(value);
    }
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    // @Mapping(target = "post_id", source = "post_id")
    // @Mapping(target = "user_id", source = "user_id")
    // @Mapping(target = "comment_id", source = "comment_id")
    // @Mapping(target = "vote_type", source = "vote_type")
    Vote toVote(VoteDTO voteDTO);
    void updateVoteFromDTO(VoteDTO voteDTO, @MappingTarget Vote vote);
}