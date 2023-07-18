package pl.kurs.shapesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CircleDto extends ShapeDto{
    private double radius;
    private double area;
    private double perimeter;
}
