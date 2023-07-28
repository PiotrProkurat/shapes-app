package pl.kurs.shapesapp.security.auth;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
}
