package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.UserDTO;
import pl.pwr.scenttracker.model.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .password(user.getPassword())
                .avatarPath(user.getAvatarPath())
                .build();
    }
}
