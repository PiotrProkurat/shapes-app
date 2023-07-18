package pl.kurs.shapesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RectangleDto extends ShapeDto {
    private double width;
    private double height;
    private double area;
    private double perimeter;
}
