package pl.kurs.shapesapp.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.kurs.shapesapp.models.user.Role;

@Getter
@Setter
public class CreateUserCommand {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotNull
    private Role role;
}
