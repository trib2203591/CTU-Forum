package ctu.forum.service;

import java.util.Date;
import java.util.List;

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
    public List<User> getAllUsers() {
        // TO DO
        List<User> users = null;
        return users;
    }

    @Override
    public List<User> getUserByStudentId (String student_id) {
        // TO DO
        List<User> users = null;
        return users;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        // TO DO
        User newUser = userMapper.toUser(userDTO);

        newUser.created_at = new Date();
        newUser.updated_at = new Date();

        this.persist(newUser);
    }

    @Override
    public void updateUser(String id, UserDTO userDTO) {
        // TO DO   
    }

    @Override
    public void deleteUser(String id) {
        // TO DO
    }

}
