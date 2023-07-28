package pl.kurs.shapesapp.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kurs.shapesapp.models.user.Role;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private Role role;
}
