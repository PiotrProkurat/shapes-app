package pl.kurs.shapesapp.models.shapes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.kurs.shapesapp.commands.CreateShapeCommand;
import pl.kurs.shapesapp.commands.UpdateShapeCommand;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.dto.SquareDto;
import pl.kurs.shapesapp.exceptions.TheSameParametersException;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;
import pl.kurs.shapesapp.models.Shape;
import pl.kurs.shapesapp.models.Square;
import pl.kurs.shapesapp.models.changes.ChangeDetails;
import pl.kurs.shapesapp.models.changes.ChangeEvent;

import java.util.List;

@Component
public class SquareImpl implements IShape{

    private final ModelMapper modelMapper;

    public SquareImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public String type() {
        return "square";
    }

    @Override
    public Shape createShape(CreateShapeCommand createShapeCommand) {
        if(createShapeCommand.getParameters().size() != 1 || createShapeCommand.getParameters().get(0) <= 0){
            throw new WrongEntityParametersException("Wrong square parameters");
        }
        Square square = new Square();
        square.setType(createShapeCommand.getType().trim().toLowerCase());
        square.setWidth(createShapeCommand.getParameters().get(0));
        return square;
    }

    @Override
    public Shape updateShape(Shape shape, UpdateShapeCommand updateShapeCommand) {
        if(updateShapeCommand.getParameters().size() != 1 || updateShapeCommand.getParameters().get(0) <= 0){
            throw new WrongEntityParametersException("Wrong square parameters");
        }
        Square square = (Square) shape;
        if(square.getWidth() == updateShapeCommand.getParameters().get(0)){
            throw new TheSameParametersException("Square width to change is the same as the current one");
        }
        square.setWidth(updateShapeCommand.getParameters().get(0));
        return square;
    }

    @Override
    public ChangeEvent getChangeEvent(Shape loadedShape, UpdateShapeCommand updateShapeCommand) {
        Square loadedSquare = (Square) loadedShape;
        ChangeDetails changeDetails = new ChangeDetails("width", loadedSquare.getWidth(), updateShapeCommand.getParameters().get(0));
        ChangeEvent changeEvent = new ChangeEvent(loadedShape.getId());
        changeEvent.addChangeDetails(List.of(changeDetails));
        return changeEvent;
    }

    @Override
    public ShapeDto response(Shape shape) {
        SquareDto squareDto = (modelMapper.map(shape, SquareDto.class));
        squareDto.setArea(squareDto.getWidth() * squareDto.getWidth());
        squareDto.setPerimeter(squareDto.getWidth() * 4);
        return squareDto;
    }


}
