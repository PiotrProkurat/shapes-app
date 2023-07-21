package pl.kurs.shapesapp.dto;

import lombok.*;

@Getter
@Setter
public class SquareDto extends ShapeDto {
    private double width;
    private double area;
    private double perimeter;
}
