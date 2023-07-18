package pl.kurs.shapesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ShapeDto {
    private Long id;
    private String type;
    private int version;
}
