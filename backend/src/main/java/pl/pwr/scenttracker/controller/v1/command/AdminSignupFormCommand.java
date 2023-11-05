package pl.pwr.scenttracker.controller.v1.command;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class AdminSignupFormCommand {

    @NotBlank
    private String login;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5)
    private String password;
}
