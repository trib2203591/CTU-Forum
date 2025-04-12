package ctu.forum.interactor.in;

import java.util.List;

import ctu.forum.dto.SecuredUserDTO;
import ctu.forum.dto.UserDTO;
import ctu.forum.model.User;

public interface IUserService {
    User getUserByStudentId(String student_id);
    List<User> findUserByName(String name);
    void createUser(UserDTO userDTO);
    void deleteUser(String student_id);
}
