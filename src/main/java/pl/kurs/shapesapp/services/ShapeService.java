package pl.kurs.shapesapp.services;

import org.springframework.stereotype.Service;
import pl.kurs.shapesapp.commands.CreateShapeCommands;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.exceptions.NotFoundException;
import pl.kurs.shapesapp.exceptions.UnknownShapeException;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;
import pl.kurs.shapesapp.models.Shape;
import pl.kurs.shapesapp.models.shapes.IShape;
import pl.kurs.shapesapp.repositories.ShapeRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShapeService {
    private final ShapeRepository shapeRepository;
    private final Map<String, IShape> shapes;

    public ShapeService(ShapeRepository shapeRepository, Set<IShape> allShapes) {
        this.shapeRepository = shapeRepository;
        this.shapes = allShapes.stream().collect(Collectors.toMap(IShape::type, Function.identity()));
    }

    public Shape create(CreateShapeCommands createShapeCommands){
        if(!shapes.containsKey(createShapeCommands.getType())){
            throw new UnknownShapeException("Unknown shape type: " + createShapeCommands.getType());
        }
        Shape shape = shapes.get(createShapeCommands.getType()).createShape(createShapeCommands);
        return shapeRepository.save(
                Optional.ofNullable(shape)
                .filter(x -> Objects.isNull(x.getId()))
                .orElseThrow(() -> new WrongEntityParametersException("Wrong entity for persist!")));
    }

    public ShapeDto getResponse(Shape shape){
        return shapes.get(shape.getType()).response(shape);
    }

}
