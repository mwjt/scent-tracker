package pl.pwr.scent_tracker.controller.v1.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.pwr.scent_tracker.controller.v1.command.AdminSignupFormCommand;
import pl.pwr.scent_tracker.model.dto.entity.UserDTO;
import pl.pwr.scent_tracker.model.entity.User;
import pl.pwr.scent_tracker.model.entity.UserRole;
import pl.pwr.scent_tracker.service.UserService;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping(value = {"/logout"})
    public ModelAndView logout() {
        return new ModelAndView("logout");
    }

    @GetMapping(value = {"/home"})
    public String home() {
        return "redirect:dashboard";
    }

    @PostMapping(value = "/signup")
    public ModelAndView createAdmin(@Valid @ModelAttribute("adminSignupFormData") AdminSignupFormCommand adminSignupFormCommand, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("signup");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        try {
            User user = registerAdmin(adminSignupFormCommand);
        } catch (Exception ex) {
            bindingResult.rejectValue("login", "error.adminSignupFormCommand", ex.getMessage());
            return modelAndView;
        }
        return new ModelAndView("login");
    }

    
    private User registerAdmin(@Valid AdminSignupFormCommand adminSignupFormCommand) throws Exception {
        return userService.signup(new UserDTO()
                .setLogin(adminSignupFormCommand.getLogin())
                .setEmail(adminSignupFormCommand.getEmail())
                .setPassword(adminSignupFormCommand.getPassword())
                .setRole(UserRole.ADMIN)
        );
    }
}
