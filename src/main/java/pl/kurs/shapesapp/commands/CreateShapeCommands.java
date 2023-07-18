package pl.kurs.shapesapp.commands;

import lombok.Getter;
import lombok.Setter;
import pl.kurs.shapesapp.validators.CheckShape;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class CreateShapeCommands {
    @NotBlank(message = "SHAPE_NOT_PROVIDED")
    @CheckShape
    private String type;
    private List<Double> parameters;
}
