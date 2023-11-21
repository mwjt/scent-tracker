package pl.pwr.scent_tracker.controller.v1.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.pwr.scent_tracker.controller.v1.command.PasswordFormCommand;
import pl.pwr.scent_tracker.controller.v1.command.ProfileFormCommand;
import pl.pwr.scent_tracker.model.dto.entity.UserDTO;
import pl.pwr.scent_tracker.service.UserService;

import javax.validation.Valid;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView("dashboard");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDTO userDTO = userService.getUserByLogin(auth.getName());
//        modelAndView.addObject("currentUser", userDTO);
//        modelAndView.addObject("userName", userDTO.getLogin());
        return modelAndView;
    }

    @PostMapping(value = "/profile")
    public ModelAndView updateProfile(@Valid @ModelAttribute("profileForm") ProfileFormCommand profileFormCommand, BindingResult bindingResult) throws Exception {
        ModelAndView modelAndView = new ModelAndView("profile");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDTO userDTO = userService.getUserByLogin(auth.getName());
//        PasswordFormCommand passwordFormCommand = new PasswordFormCommand()
//                .setLogin(userDTO.getLogin())
//                .setPassword(userDTO.getPassword());
//        modelAndView.addObject("passwordForm", passwordFormCommand);
//        if (!bindingResult.hasErrors()) {
//            userDTO
//                    .setLogin(profileFormCommand.getLogin())
//                    .setEmail(profileFormCommand.getEmail());
//            userService.setUserLoginAndEmail(userDTO);
//        }
        return modelAndView;
    }

    @PostMapping(value = "/password")
    public ModelAndView changePassword(@Valid @ModelAttribute("passwordForm") PasswordFormCommand passwordFormCommand, BindingResult bindingResult) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDTO userDTO = userService.getUserByLogin(auth.getName());
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("profile");
//            ProfileFormCommand profileFormCommand = new ProfileFormCommand()
//                    .setLogin(userDTO.getLogin())
//                    .setEmail(userDTO.getEmail());
//            modelAndView.addObject("profileForm", profileFormCommand);
            return modelAndView;
        }
//        userService.setUserPassword(userDTO, passwordFormCommand.getPassword());
        return new ModelAndView("login");
    }
}
