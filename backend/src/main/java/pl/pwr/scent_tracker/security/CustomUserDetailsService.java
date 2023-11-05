package pl.pwr.scent_tracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pwr.scent_tracker.model.dto.entity.UserDTO;
import pl.pwr.scent_tracker.service.UserService;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findUserByLogin(username);
        if (userDTO == null) throw new UsernameNotFoundException("User " + username + " not found");
        return buildUserForAuthentication(userDTO);
    }

    private UserDetails buildUserForAuthentication(UserDTO userDTO) {
        return new org.springframework.security.core.userdetails.User(userDTO.getLogin(), userDTO.getPassword(), new ArrayList<>());//TODO
    }
}
