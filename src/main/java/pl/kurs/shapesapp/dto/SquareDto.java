package pl.kurs.shapesapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SquareDto extends ShapeDto {
    private double width;
    private double area;
    private double perimeter;
}
