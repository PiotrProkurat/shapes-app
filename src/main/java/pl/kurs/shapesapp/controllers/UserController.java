package pl.kurs.shapesapp.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.shapesapp.commands.CreateUserCommand;
import pl.kurs.shapesapp.dto.UserDto;
import pl.kurs.shapesapp.models.user.User;
import pl.kurs.shapesapp.services.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserCommand createUserCommand){
        User user = userService.create(createUserCommand);
        return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(@PageableDefault(sort = "firstName", direction = Sort.Direction.ASC) Pageable pageable){
        Page<User> users = userService.getAllUsers(pageable);
        Page<UserDto> usersDto = new PageImpl<>(users.stream()
                .map(x -> modelMapper.map(x, UserDto.class))
                .collect(Collectors.toList()), pageable, users.getTotalElements());
        return ResponseEntity.ok(usersDto);
    }


}
