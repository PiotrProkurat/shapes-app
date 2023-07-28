package pl.kurs.shapesapp.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateShapeCommand {
    private List<Double> parameters;
}
