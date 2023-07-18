package pl.kurs.shapesapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.shapesapp.commands.CreateShapeCommands;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.models.Shape;
import pl.kurs.shapesapp.services.ShapeService;

@RestController
@RequestMapping("/api/v1/shapes")
@RequiredArgsConstructor
public class ShapeController {
    private final ShapeService shapeService;

    @PostMapping
    public ResponseEntity<ShapeDto> createShape(@RequestBody CreateShapeCommands createShapeCommands){
        Shape shape = shapeService.create(createShapeCommands);
        return new ResponseEntity<>(shapeService.getResponse(shape), HttpStatus.CREATED);
    }
}
