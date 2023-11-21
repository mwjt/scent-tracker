package pl.pwr.scent_tracker.service;

import pl.pwr.scent_tracker.model.dto.entity.UserDTO;
import pl.pwr.scent_tracker.model.entity.Gallery;
import pl.pwr.scent_tracker.model.entity.User;

public interface UserService {
    User signup(UserDTO userDTO) throws Exception;
    User getUserByLogin(String login) throws Exception;
    User getUserByEmail(String email) throws Exception;
    User setUserLoginAndEmail(User user) throws Exception;
    User setUserPassword(User user, String newPassword) throws Exception;
    User getUserById(Long id) throws Exception;
    User setUserPhoto(User user, Gallery gallery);
    User setUserRole(User user, String role) throws Exception;
}
