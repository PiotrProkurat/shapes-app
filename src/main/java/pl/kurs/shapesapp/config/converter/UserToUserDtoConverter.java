package pl.kurs.shapesapp.config.converter;



import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import pl.kurs.shapesapp.dto.UserDto;
import pl.kurs.shapesapp.models.user.User;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(MappingContext<User, UserDto> mappingContext) {
        User source = mappingContext.getSource();
        return UserDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .login(source.getLogin())
                .role(source.getRole())
                .amountOfShapes(source.getShapes().size())
                .build();
    }
}
