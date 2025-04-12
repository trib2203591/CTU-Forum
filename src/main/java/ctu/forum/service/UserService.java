package ctu.forum.service;

import java.util.Date;
import java.util.List;

import ctu.forum.dto.SecuredUserDTO;
import ctu.forum.dto.UserDTO;
import ctu.forum.interactor.in.IUserService;
import ctu.forum.interactor.mapper.IUserMapper;
import ctu.forum.model.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService implements IUserService, PanacheMongoRepository<User> {

    @Inject 
    IUserMapper userMapper;

    @Override
    public User getUserByStudentId (String student_id) {
        User user = this.find("student_id", student_id).firstResult();
        if (user == null) {
            throw new IllegalArgumentException("User with student_id " + student_id + " not found.");
        }
        return user;
    }

    @Override
    public List<User> findUserByName(String name) {
        List<User> users = this.find("{ $text : { $search : ?1 } }", name).list();
        if(users.isEmpty()) {
            throw new IllegalArgumentException("User with name " + name + " not found.");
        }
        // List<SecuredUserDTO> securedUsers = users.stream()
        //         .map(userMapper::toSecuredUserDTO)
        //         .toList();
        return users;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        try {
            if (!userDTO.validate()) {
                throw new IllegalArgumentException("User info is not valid.");
            }
            if (this.find("student_id", userDTO.getStudent_id()).firstResult() != null) {
                throw new IllegalArgumentException("User with student_id " + userDTO.getStudent_id() + " already exists.");
            }
            User newUser = userMapper.toUser(userDTO);
    
            newUser.created_at = new Date();
            newUser.updated_at = new Date();
    
            this.persist(newUser);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
        

    @Override
    public void deleteUser(String student_id) {
        try {
            User userToDelete = this.find("student_id", student_id).firstResult();
            if (userToDelete == null) {
                throw new IllegalArgumentException("User with student_id " + student_id + " not found.");
            } 
            this.delete(userToDelete);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
