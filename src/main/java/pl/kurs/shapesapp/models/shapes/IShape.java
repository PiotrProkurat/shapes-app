package pl.kurs.shapesapp.models.shapes;

import pl.kurs.shapesapp.commands.CreateShapeCommand;
import pl.kurs.shapesapp.commands.UpdateShapeCommand;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.models.Shape;
import pl.kurs.shapesapp.models.changes.ChangeEvent;

public interface IShape {
    String type();
    Shape createShape(CreateShapeCommand createShapeCommand);
    Shape updateShape(Shape shape, UpdateShapeCommand updateShapeCommand);
    ChangeEvent getChangeEvent(Shape loadedShape, UpdateShapeCommand updateShapeCommand);
    ShapeDto response(Shape shape);
}
