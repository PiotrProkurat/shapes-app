package pl.kurs.shapesapp.models.shapes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.kurs.shapesapp.commands.CreateShapeCommand;
import pl.kurs.shapesapp.commands.UpdateShapeCommand;
import pl.kurs.shapesapp.dto.CircleDto;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.exceptions.TheSameParametersException;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;
import pl.kurs.shapesapp.models.Circle;
import pl.kurs.shapesapp.models.Shape;
import pl.kurs.shapesapp.models.changes.ChangeDetails;
import pl.kurs.shapesapp.models.changes.ChangeEvent;

import java.util.List;

@Component
public class CircleImpl implements IShape {

    private final ModelMapper modelMapper;

    public CircleImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public String type() {
        return "circle";
    }

    @Override
    public Shape createShape(CreateShapeCommand createShapeCommand) {
        if (createShapeCommand.getParameters().size() != 1 || createShapeCommand.getParameters().get(0) <= 0) {
            throw new WrongEntityParametersException("Wrong circle parameters");
        }
        Circle circle = new Circle();
        circle.setType(createShapeCommand.getType().trim().toLowerCase());
        circle.setRadius(createShapeCommand.getParameters().get(0));
        return circle;
    }

    @Override
    public Shape updateShape(Shape shape, UpdateShapeCommand updateShapeCommand) {
        if (updateShapeCommand.getParameters().size() != 1 || updateShapeCommand.getParameters().get(0) <= 0) {
            throw new WrongEntityParametersException("Wrong circle parameters");
        }
        Circle circle = (Circle) shape;
        if (circle.getRadius() == updateShapeCommand.getParameters().get(0)) {
            throw new TheSameParametersException("Circle radius to change is the same as the current one");
        }
        circle.setRadius(updateShapeCommand.getParameters().get(0));
        return circle;
    }

    @Override
    public ChangeEvent getChangeEvent(Shape loadedShape, UpdateShapeCommand updateShapeCommand) {
        Circle loadedCircle = (Circle) loadedShape;
        ChangeDetails changeDetails = new ChangeDetails("radius", loadedCircle.getRadius(), updateShapeCommand.getParameters().get(0));
        ChangeEvent changeEvent = new ChangeEvent(loadedShape.getId());
       changeEvent.addChangeDetails(List.of(changeDetails));
        return changeEvent;
    }


    @Override
    public ShapeDto response(Shape shape) {
        CircleDto circleDto = (modelMapper.map(shape, CircleDto.class));
        circleDto.setArea(Math.PI * Math.pow(circleDto.getRadius(), 2));
        circleDto.setPerimeter(2 * Math.PI * circleDto.getRadius());
        return circleDto;
    }


}
