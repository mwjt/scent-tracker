package pl.pwr.scenttracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pwr.scenttracker.dto.entity.RoleDTO;
import pl.pwr.scenttracker.dto.entity.UserDTO;
import pl.pwr.scenttracker.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findUserByLogin(username);
        if (userDTO == null) throw new UsernameNotFoundException("User " + username + " not found");
        return buildUserForAuthentication(userDTO, getUserAuthority(userDTO.getRoles()));
    }

    private List<GrantedAuthority> getUserAuthority(List<RoleDTO> userRoles) {
        List<GrantedAuthority> roles = new ArrayList<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });
        return roles;
    }

    private UserDetails buildUserForAuthentication(UserDTO userDTO, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(userDTO.getLogin(), userDTO.getPassword(), authorities);
    }
}
