package pl.kurs.shapesapp.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.kurs.shapesapp.models.shapes.IShape;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckShapeValidator implements ConstraintValidator<CheckShape, String> {
    private final Set<String> shapes;

    public CheckShapeValidator(Set<IShape> shapes) {
        this.shapes = shapes.stream().map(IShape::type).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .map(shapes::contains)
                .orElse(true);
    }
}
