package pl.kurs.shapesapp.commands;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import pl.kurs.shapesapp.validators.CheckShape;


import java.util.List;

@Getter
@Setter
public class CreateShapeCommand {
    @NotBlank(message = "SHAPE_NOT_PROVIDED")
    @CheckShape
    private String type;
    private List<Double> parameters;
}
