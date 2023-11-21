package pl.pwr.scent_tracker.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pwr.scent_tracker.model.dto.entity.UserDTO;
import pl.pwr.scent_tracker.model.dto.mapper.UserMapper;
import pl.pwr.scent_tracker.model.entity.Gallery;
import pl.pwr.scent_tracker.model.entity.User;
import pl.pwr.scent_tracker.model.entity.UserRole;
import pl.pwr.scent_tracker.repository.GalleryRepository;
import pl.pwr.scent_tracker.repository.UserRepository;
import pl.pwr.scent_tracker.service.UserService;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /** Signup user
     *
     * @param userDTO
     * @return
     * @throws Exception
     */
    @Override
    public User signup(UserDTO userDTO) throws Exception {

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
        return userRepository.save(user);
    }

    /** Find existing user using login
     *
     * @param login
     * @return
     */
    @Override
    public User getUserByLogin(String login) throws Exception {
        User user = userRepository.findByLogin(login);
        if (user == null) throw new Exception("User not found");
        return user;
    }

    /** Find existing user using email
     *
     * @param email
     * @return
     */
    @Override
    public User getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new Exception("User not found");
        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) throw new Exception("User not found");
        return user;
    }

    /** Update profile
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public User setUserLoginAndEmail(User user) throws Exception {
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        if (oldUser == null) throw new Exception("User not found");
        oldUser.setEmail(user.getEmail())
                .setLogin(user.getLogin());
        return userRepository.save(oldUser);
    }

    /** Change password
     *
     * @param user
     * @param newPassword
     * @return
     * @throws Exception
     */
    @Override
    public User setUserPassword(User user, String newPassword) throws Exception {
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        if (oldUser == null) throw new Exception("User not found");
        oldUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        return userRepository.save(oldUser);
    }

    @Override
    public User setUserPhoto(User user, Gallery gallery) {
        user.setGallery(gallery);
        return userRepository.save(user);
    }

    @Override
    public User setUserRole(User user, String role) throws Exception {
        User oldUser = getUserById(user.getId());
        if (oldUser == null) throw new Exception("User not found");
        if (role.equalsIgnoreCase("user")) {
            oldUser.setRole(UserRole.USER);
        } else if (role.equalsIgnoreCase("moderator")) {
            oldUser.setRole(UserRole.MODERATOR);
        } else if (role.equalsIgnoreCase("admin")) {
            oldUser.setRole(UserRole.ADMIN);
        } else throw new Exception("Role not found");
        return userRepository.save(oldUser);
    }


}
