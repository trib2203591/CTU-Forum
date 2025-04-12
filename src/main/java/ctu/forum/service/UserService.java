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
        return user;
    }

    @Override
    public List<SecuredUserDTO> findUserByName(String name) {
        List<User> users = this.find("name", name).list();
        List<SecuredUserDTO> securedUsers = users.stream()
                .map(userMapper::toSecuredUserDTO)
                .toList();
        return securedUsers;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        User newUser = userMapper.toUser(userDTO);

        newUser.created_at = new Date();
        newUser.updated_at = new Date();

        this.persist(newUser);
    }

    @Override
    public void deleteUser(String student_id) {
        User userToDelete = this.find("student_id", student_id).firstResult();
        if (userToDelete != null) {
            this.delete(userToDelete);
        } else {
            throw new IllegalArgumentException("User with student_id " + student_id + " not found.");
        }
    }

}
