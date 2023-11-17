package pl.pwr.scent_tracker.controller.v1;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pl.pwr.scent_tracker.model.dto.entity.UserDTO;
import pl.pwr.scent_tracker.service.UserService;
import pl.pwr.scent_tracker.utils.AppConstants;

@RestController
@RequestMapping(path = "/api/v1/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "/{login}")
    public ResponseEntity get(@PathVariable(value = "login") String login) {
        UserDTO user = userService.findUserByLogin(login);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

}
