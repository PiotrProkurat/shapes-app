package pl.kurs.shapesapp.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint(validatedBy = CheckShapeValidator.class)
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckShape {
    String message() default "This shape is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
