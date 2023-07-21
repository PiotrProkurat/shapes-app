package pl.kurs.shapesapp.models.shapes;

import com.querydsl.core.BooleanBuilder;
import pl.kurs.shapesapp.commands.CreateShapeCommands;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.models.Shape;

public interface IShape {
    String type();
    Shape createShape(CreateShapeCommands createShapeCommands);
    ShapeDto response(Shape shape);
}
