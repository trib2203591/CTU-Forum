package ctu.forum.interactor.in;

import java.util.List;

import ctu.forum.dto.UserDTO;
import ctu.forum.model.User;

public interface IUserService {
    List<User> getAllUsers();
    List<User> getUserByStudentId(String student_id);
    void createUser(UserDTO userDTO);
    void updateUser(String id, UserDTO userDTO);
    void deleteUser(String id);
}
