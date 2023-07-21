package pl.kurs.shapesapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CircleDto extends ShapeDto{
    private double radius;
    private double area;
    private double perimeter;
}
