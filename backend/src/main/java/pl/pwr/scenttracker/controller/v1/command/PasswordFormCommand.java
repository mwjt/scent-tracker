package pl.pwr.scenttracker.controller.v1.command;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class PasswordFormCommand {

    private String login;

    @NotBlank
    @Size(min = 5)
    private String password;
}
