package ctu.forum.interactor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ctu.forum.dto.UserDTO;
import ctu.forum.model.User;

@Mapper(componentModel = "cdi")
public interface IUserMapper {
    
    @Mapping(target="id", ignore = true)
    @Mapping(target="created_at", ignore = true)
    @Mapping(target="updated_at", ignore = true)
    //TO DO: @Mapping(target="password_hash", ignore = true) // Prevent mapping raw passwords
    User toUser(UserDTO userDTO);

}
