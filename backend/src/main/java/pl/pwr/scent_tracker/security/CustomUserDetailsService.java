package pl.pwr.scent_tracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pwr.scent_tracker.model.dto.entity.UserDTO;
import pl.pwr.scent_tracker.model.dto.mapper.UserMapper;
import pl.pwr.scent_tracker.model.entity.User;
import pl.pwr.scent_tracker.service.UserService;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByLogin(username);
            return buildUserForAuthentication(UserMapper.toUserDTO(user));
        } catch (Exception e) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

    }

    private UserDetails buildUserForAuthentication(UserDTO userDTO) {
        return new org.springframework.security.core.userdetails.User(userDTO.getLogin(), userDTO.getPassword(), new ArrayList<>());//TODO
    }
}
