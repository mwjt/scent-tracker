package pl.pwr.scent_tracker.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pwr.scent_tracker.model.dto.entity.UserDTO;
import pl.pwr.scent_tracker.model.dto.mapper.UserMapper;
import pl.pwr.scent_tracker.model.entity.User;
import pl.pwr.scent_tracker.model.entity.UserRole;
import pl.pwr.scent_tracker.repository.UserRepository;
import pl.pwr.scent_tracker.service.UserService;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /** Signup user
     *
     * @param userDTO
     * @return
     * @throws Exception
     */
    @Override
    public UserDTO signup(UserDTO userDTO) throws Exception {

        User user;
        user = userRepository.findByEmail(userDTO.getEmail());
        if (user != null) throw new Exception("Email " + userDTO.getEmail() + " already used!");
        user = userRepository.findByLogin(userDTO.getLogin());
        if (user != null) throw new Exception("Login " + userDTO.getLogin() + " already used!");

        user = User.builder()
                .email(userDTO.getEmail())
                .login(userDTO.getLogin())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .role(UserRole.USER)
                .build();
        return UserMapper.toUserDTO(userRepository.save(user));
    }

    /** Find existing user using login
     *
     * @param login
     * @return
     */
    @Override
    public UserDTO findUserByLogin(String login) {

        return UserMapper.toUserDTO(userRepository.findByLogin(login));

    }

    /** Find existing user using email
     *
     * @param email
     * @return
     */
    @Override
    public UserDTO findUserByEmail(String email) {

        return UserMapper.toUserDTO(userRepository.findByEmail(email));
    }

    /** Update profile
     *
     * @param userDTO
     * @return
     * @throws Exception
     */
    @Override
    public UserDTO updateProfile(UserDTO userDTO) throws Exception {

        Optional<User> user = Optional.ofNullable(userRepository.findByLogin(userDTO.getLogin()));
        if (user.isEmpty()) throw new Exception("Error - user" + userDTO.getLogin() + " not found");
        User baseUser = user.get();
        baseUser.setEmail(userDTO.getEmail())
                .setLogin(userDTO.getLogin())
                .setAvatarPath(userDTO.getAvatarPath());
        return UserMapper.toUserDTO(userRepository.save(baseUser));
    }

    /** Change password
     *
     * @param userDTO
     * @param newPassword
     * @return
     * @throws Exception
     */
    @Override
    public UserDTO changePassword(UserDTO userDTO, String newPassword) throws Exception {

        Optional<User> user = Optional.ofNullable(userRepository.findByLogin(userDTO.getLogin()));
        if (user.isEmpty()) throw new Exception("Error - user" + userDTO.getLogin() + " not found");
        User baseUser = user.get();
        baseUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        return UserMapper.toUserDTO(userRepository.save(baseUser));
    }
}
