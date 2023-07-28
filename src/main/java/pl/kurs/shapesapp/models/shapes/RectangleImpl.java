package pl.kurs.shapesapp.models.shapes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.kurs.shapesapp.commands.CreateShapeCommand;
import pl.kurs.shapesapp.commands.UpdateShapeCommand;
import pl.kurs.shapesapp.dto.RectangleDto;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.exceptions.TheSameParametersException;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;
import pl.kurs.shapesapp.models.Rectangle;
import pl.kurs.shapesapp.models.Shape;
import pl.kurs.shapesapp.models.Square;
import pl.kurs.shapesapp.models.changes.ChangeDetails;
import pl.kurs.shapesapp.models.changes.ChangeEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RectangleImpl implements IShape{

    private final ModelMapper modelMapper;

    public RectangleImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public String type() {
        return "rectangle";
    }

    @Override
    public Shape createShape(CreateShapeCommand createShapeCommand) {
        if(createShapeCommand.getParameters().size() != 2 || createShapeCommand.getParameters().get(0) <= 0 || createShapeCommand.getParameters().get(1) <= 0){
            throw new WrongEntityParametersException("Wrong rectangle parameters");
        }
        Rectangle rectangle = new Rectangle();
        rectangle.setType(createShapeCommand.getType().trim().toLowerCase());
        rectangle.setWidth(createShapeCommand.getParameters().get(0));
        rectangle.setHeight(createShapeCommand.getParameters().get(1));
        return rectangle;
    }

    @Override
    public Shape updateShape(Shape shape, UpdateShapeCommand updateShapeCommand) {
        if(updateShapeCommand.getParameters().size() != 2 || updateShapeCommand.getParameters().get(0) <= 0 || updateShapeCommand.getParameters().get(1) <= 0){
            throw new WrongEntityParametersException("Wrong rectangle parameters");
        }
        Rectangle rectangle = (Rectangle) shape;
        if(rectangle.getWidth() == updateShapeCommand.getParameters().get(0) && rectangle.getHeight() == updateShapeCommand.getParameters().get(1)){
            throw new TheSameParametersException("Rectangle width and height to change are the same as the current ones");
        }
        rectangle.setWidth(updateShapeCommand.getParameters().get(0));
        rectangle.setHeight(updateShapeCommand.getParameters().get(1));
        return rectangle;
    }

    @Override
    public ChangeEvent getChangeEvent(Shape loadedShape, UpdateShapeCommand updateShapeCommand) {
        Rectangle loadedRectangle = (Rectangle) loadedShape;
        List<ChangeDetails> changeDetails = new ArrayList<>();
        if(loadedRectangle.getWidth() != updateShapeCommand.getParameters().get(0)){
            changeDetails.add(new ChangeDetails("width", loadedRectangle.getWidth(), updateShapeCommand.getParameters().get(0)));
        }
        if(loadedRectangle.getHeight() != updateShapeCommand.getParameters().get(1)){
            changeDetails.add(new ChangeDetails("height", loadedRectangle.getHeight(), updateShapeCommand.getParameters().get(1)));
        }
        return new ChangeEvent(LocalDateTime.now(), loadedShape.getId(), changeDetails, "ADMIN");
    }

    @Override
    public ShapeDto response(Shape shape) {
        RectangleDto rectangleDto = (modelMapper.map(shape, RectangleDto.class));
        rectangleDto.setArea(rectangleDto.getHeight() * rectangleDto.getWidth());
        rectangleDto.setPerimeter((rectangleDto.getWidth() * 2) + (rectangleDto.getHeight() * 2));
        return rectangleDto;
    }
}
