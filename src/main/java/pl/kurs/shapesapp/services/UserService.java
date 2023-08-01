package pl.kurs.shapesapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kurs.shapesapp.commands.CreateUserCommand;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;
import pl.kurs.shapesapp.models.user.User;
import pl.kurs.shapesapp.repositories.UserRepository;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }


    public User create(CreateUserCommand createUserCommand) {
        User user = modelMapper.map(createUserCommand, User.class);
        user.setPassword(passwordEncoder.encode(createUserCommand.getPassword()));
        return userRepository.save(
                Optional.ofNullable(user)
                        .filter(x -> Objects.isNull(x.getId()))
                        .orElseThrow(() -> new WrongEntityParametersException("Wrong entity for persist!")));
    }

    public Page<User> getAllUsers(Pageable pageable) {
        List<User> users = userRepository.findUsersWithShapes();
        return new PageImpl<>(users, pageable, users.size());
    }

}
