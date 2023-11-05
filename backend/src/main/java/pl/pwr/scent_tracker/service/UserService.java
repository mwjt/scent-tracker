package pl.pwr.scent_tracker.service;

import pl.pwr.scent_tracker.model.dto.entity.UserDTO;

public interface UserService {
    UserDTO signup(UserDTO userDTO) throws Exception;
    UserDTO findUserByLogin(String login);
    UserDTO findUserByEmail(String email);
    UserDTO updateProfile(UserDTO userDTO) throws Exception;
    UserDTO changePassword(UserDTO userDTO, String newPassword) throws Exception;
}
