package pl.kurs.shapesapp.security.auth;

import lombok.*;

@Getter
@Setter
public class AuthenticationRequest {
    private String login;
    private String password;
}
