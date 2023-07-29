package pl.kurs.shapesapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.kurs.shapesapp.models.user.Role;

@Builder
@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private Role role;
    private int amountOfShapes;
}
