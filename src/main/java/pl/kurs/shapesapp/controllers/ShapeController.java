package pl.kurs.shapesapp.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.kurs.shapesapp.commands.CreateShapeCommand;
import pl.kurs.shapesapp.commands.UpdateShapeCommand;
import pl.kurs.shapesapp.dto.ChangeEventDto;
import pl.kurs.shapesapp.dto.ShapeDto;
import pl.kurs.shapesapp.models.Shape;
import pl.kurs.shapesapp.models.changes.ChangeEvent;
import pl.kurs.shapesapp.services.ChangeEventService;
import pl.kurs.shapesapp.services.ShapeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/shapes")
@RequiredArgsConstructor
public class ShapeController {
    private final ShapeService shapeService;
    private final ChangeEventService changeEventService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ShapeDto> createShape(@RequestBody CreateShapeCommand createShapeCommand) {
        Shape shape = shapeService.create(createShapeCommand);
        return new ResponseEntity<>(shapeService.getResponse(shape), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ShapeDto>> getAllShape(@PageableDefault(sort = "type", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam Map<String, String> parameters) {
        Page<Shape> shapes = shapeService.getAllShape(pageable, parameters);
        Page<ShapeDto> shapesDto = shapes.map(shapeService::getResponse);
        return ResponseEntity.ok(shapesDto);
    }

    @PreAuthorize("hasAuthority('ADMIN') or @shapeService.isShapeCreatedByCurrentUser(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<ShapeDto> updateShape(@PathVariable Long id, @RequestBody UpdateShapeCommand updateShapeCommand) {
        Shape shape = shapeService.edit(id, updateShapeCommand);
        return new ResponseEntity<>(shapeService.getResponse(shape), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or @shapeService.isShapeCreatedByCurrentUser(#id)")
    @GetMapping("/{id}/changes")
    public ResponseEntity<Page<ChangeEventDto>> getShapeChanges(@PageableDefault(sort = "date", direction = Sort.Direction.ASC) Pageable pageable, @PathVariable Long id) {
        Page<ChangeEvent> changeEvents = changeEventService.getAllChangesShapeWithId(pageable, id);
        Page<ChangeEventDto> changeEventsDto = changeEvents.map(x -> modelMapper.map(x, ChangeEventDto.class));
        return ResponseEntity.ok(changeEventsDto);
    }


}
