package pl.pwr.scent_tracker.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.pwr.scent_tracker.model.api.ErrorRes;
import pl.pwr.scent_tracker.model.api.LoginReq;
import pl.pwr.scent_tracker.model.api.LoginRes;
import pl.pwr.scent_tracker.model.api.RegisterReq;
import pl.pwr.scent_tracker.model.dto.entity.UserDTO;
import pl.pwr.scent_tracker.security.JwtUtil;
import pl.pwr.scent_tracker.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/auth/")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginReq.getLogin(),
                            loginReq.getPassword())
            );
            UserDTO user = userService.findUserByLogin(loginReq.getLogin());
            LoginRes loginRes = new LoginRes(user.getLogin(), jwtUtil.createToken(user));
            return ResponseEntity.ok(loginRes);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorRes(HttpStatus.UNAUTHORIZED, "Invalid username or password"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorRes(HttpStatus.BAD_REQUEST, ex.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity signup(@RequestBody RegisterReq req) {
        UserDTO userDTO = UserDTO.builder()
                .email(req.getEmail())
                .login(req.getLogin())
                .password(req.getPassword())
                .build();
        try {
            UserDTO registeredUser = userService.signup(userDTO);
            return ResponseEntity.ok("ok");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorRes(HttpStatus.BAD_REQUEST, ex.getMessage()));
        }
    }

}
