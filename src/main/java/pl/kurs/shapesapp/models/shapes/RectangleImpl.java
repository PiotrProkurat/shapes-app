package pl.kurs.shapesapp.models.shapes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.kurs.shapesapp.commands.CreateShapeCommands;
import pl.kurs.shapesapp.dto.RectangleDto;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;
import pl.kurs.shapesapp.models.Rectangle;
import pl.kurs.shapesapp.models.Shape;

@Component
public class RectangleImpl implements IShape{

    private final ModelMapper modelMapper;

    public RectangleImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public String type() {
        return "Rectangle";
    }

    @Override
    public Shape createShape(CreateShapeCommands createShapeCommands) {
        if(createShapeCommands.getParameters().size() != 2 || createShapeCommands.getParameters().get(0) <= 0 || createShapeCommands.getParameters().get(1) <= 0){
            throw new WrongEntityParametersException("Wrong rectangle parameters");
        }
        Rectangle rectangle = new Rectangle();
        rectangle.setType(createShapeCommands.getType());
        rectangle.setWidth(createShapeCommands.getParameters().get(0));
        rectangle.setHeight(createShapeCommands.getParameters().get(1));
        return rectangle;
    }

    @Override
    public ShapeDto response(Shape shape) {
        RectangleDto rectangleDto = (modelMapper.map(shape, RectangleDto.class));
        rectangleDto.setArea(rectangleDto.getHeight() * rectangleDto.getWidth());
        rectangleDto.setPerimeter((rectangleDto.getWidth() * 2) + (rectangleDto.getHeight() * 2));
        return rectangleDto;
    }
}
