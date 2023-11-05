package pl.pwr.scent_tracker.controller.v1.command;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class ProfileFormCommand {

    @NotBlank
    private String login;

    @NotBlank
    @Email
    private String email;
}
