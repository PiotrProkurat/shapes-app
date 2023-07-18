package pl.kurs.shapesapp.models.shapes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.kurs.shapesapp.commands.CreateShapeCommands;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.dto.SquareDto;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;
import pl.kurs.shapesapp.models.Shape;
import pl.kurs.shapesapp.models.Square;

@Component
public class SquareImpl implements IShape{

    private final ModelMapper modelMapper;

    public SquareImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public String type() {
        return "Square";
    }

    @Override
    public Shape createShape(CreateShapeCommands createShapeCommands) {
        if(createShapeCommands.getParameters().size() != 1 || createShapeCommands.getParameters().get(0) <= 0){
            throw new WrongEntityParametersException("Wrong square parameters");
        }
        Square square = new Square();
        square.setType(createShapeCommands.getType());
        square.setWidth(createShapeCommands.getParameters().get(0));
        return square;
    }

    @Override
    public ShapeDto response(Shape shape) {
        SquareDto squareDto = (modelMapper.map(shape, SquareDto.class));
        squareDto.setArea(squareDto.getWidth() * squareDto.getWidth());
        squareDto.setPerimeter(squareDto.getWidth() * 4);
        return squareDto;
    }


}
