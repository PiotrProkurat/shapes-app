package pl.kurs.shapesapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RectangleDto extends ShapeDto {
    private double width;
    private double height;
    private double area;
    private double perimeter;
}
