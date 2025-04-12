package ctu.forum.interactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ctu.forum.dto.TagDTO;
import ctu.forum.model.Tag;

@Mapper(componentModel = "cdi")
public interface ITagMapper {
    @Mapping(target = "id", ignore = true)
    Tag toTag(TagDTO tagDTO);
}