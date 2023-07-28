package pl.kurs.shapesapp.jwt;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserRepository repository;
    private ModelMapper mapper;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository repository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> addUser(@RequestBody CreateUserCommandJwt createUserCommandJwt) {
        User newUser = mapper.map(createUserCommandJwt, User.class);
        newUser.setPassword(passwordEncoder.encode(createUserCommandJwt.getPassword()));
        User savedUser = repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(savedUser, UserDto.class));
    }

}