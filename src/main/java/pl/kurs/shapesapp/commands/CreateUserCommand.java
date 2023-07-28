package pl.kurs.shapesapp.commands;

import lombok.Getter;
import lombok.Setter;
import pl.kurs.shapesapp.models.user.Role;

@Getter
@Setter
public class CreateUserCommand {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Role role;
}
