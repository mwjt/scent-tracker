package pl.pwr.scent_tracker.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.scent_tracker.model.api.ProfileRes;
import pl.pwr.scent_tracker.model.dto.entity.UserDTO;
import pl.pwr.scent_tracker.model.entity.User;
import pl.pwr.scent_tracker.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "/{login}")
    public ResponseEntity get(@PathVariable(value = "login") String login) {
        try {
            User user = userService.getUserByLogin(login);
            ProfileRes res = new ProfileRes();
            res.setLogin(user.getLogin());
            res.setEmail(user.getEmail());
            res.setRole(user.getRole().name());
            if (user.getGallery() != null) res.setGallery_id(user.getGallery().getId());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
