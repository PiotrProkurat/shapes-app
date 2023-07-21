package pl.kurs.shapesapp.models.shapes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.kurs.shapesapp.commands.CreateShapeCommands;
import pl.kurs.shapesapp.dto.CircleDto;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;
import pl.kurs.shapesapp.models.Circle;
import pl.kurs.shapesapp.models.Shape;

@Component
public class CircleImpl implements IShape{

    private final ModelMapper modelMapper;

    public CircleImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public String type() {
        return "circle";
    }

    @Override
    public Shape createShape(CreateShapeCommands createShapeCommands) {
        if(createShapeCommands.getParameters().size() != 1 || createShapeCommands.getParameters().get(0) <= 0){
            throw new WrongEntityParametersException("Wrong circle parameters");
        }
        Circle circle = new Circle();
        circle.setType(createShapeCommands.getType().trim().toLowerCase());
        circle.setRadius(createShapeCommands.getParameters().get(0));
        return circle;
    }

    @Override
    public ShapeDto response(Shape shape) {
        CircleDto circleDto = (modelMapper.map(shape, CircleDto.class));
        circleDto.setArea(Math.PI * Math.pow(circleDto.getRadius(), 2));
        circleDto.setPerimeter(2 * Math.PI * circleDto.getRadius());
        return circleDto;
    }


}
