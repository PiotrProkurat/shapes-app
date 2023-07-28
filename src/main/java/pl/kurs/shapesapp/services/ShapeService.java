package pl.kurs.shapesapp.services;


import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.shapesapp.commands.CreateShapeCommand;
import pl.kurs.shapesapp.commands.UpdateShapeCommand;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.exceptions.NotFoundException;
import pl.kurs.shapesapp.exceptions.UnknownShapeException;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;
import pl.kurs.shapesapp.models.Shape;
import pl.kurs.shapesapp.models.shapes.IShape;
import pl.kurs.shapesapp.query.FindShapesQuery;
import pl.kurs.shapesapp.repositories.ShapeRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShapeService {
    private final ShapeRepository shapeRepository;
    private final ChangeEventService changeEventService;
    private final FindShapesQuery findShapesQuery;
    private final Map<String, IShape> shapes;

    public ShapeService(ShapeRepository shapeRepository, ChangeEventService changeEventService, FindShapesQuery findShapesQuery, Set<IShape> allShapes) {
        this.shapeRepository = shapeRepository;
        this.changeEventService = changeEventService;
        this.findShapesQuery = findShapesQuery;
        this.shapes = allShapes.stream().collect(Collectors.toMap(IShape::type, Function.identity()));
    }

    public Shape create(CreateShapeCommand createShapeCommand) {
        if (!shapes.containsKey(createShapeCommand.getType().trim().toLowerCase())) {
            throw new UnknownShapeException("Unknown shape type: " + createShapeCommand.getType());
        }
        Shape shape = shapes.get(createShapeCommand.getType().trim().toLowerCase()).createShape(createShapeCommand);
        //shape.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        shape.setCreatedAt(LocalDateTime.now().withNano(0));
        return shapeRepository.save(
                Optional.ofNullable(shape)
                        .filter(x -> Objects.isNull(x.getId()))
                        .orElseThrow(() -> new WrongEntityParametersException("Wrong entity for persist!")));
    }

    public Page<Shape> getAllShape(Pageable pageable, Map<String, String> parameters) {
        Predicate predicate = findShapesQuery.toPredicate(parameters);
        return shapeRepository.findAll(predicate, pageable);
    }

    @Transactional
    public Shape edit(Long id, UpdateShapeCommand updateShapeCommand){
        Shape loadedShape = shapeRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found shape with id: " + id));
        changeEventService.create(shapes.get(loadedShape.getType()).getChangeEvent(loadedShape, updateShapeCommand));
        Shape shapeToUpdate = shapes.get(loadedShape.getType()).updateShape(loadedShape, updateShapeCommand);
        return shapeRepository.save(shapeToUpdate);
    }

    public ShapeDto getResponse(Shape shape) {
        return shapes.get(shape.getType()).response(shape);
    }

}


