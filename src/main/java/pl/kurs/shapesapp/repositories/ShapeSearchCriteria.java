package pl.kurs.shapesapp.repositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeSearchCriteria {
    private String type;
    private Double widthFrom;
    private Double widthTo;
    private Double radiusFrom;
    private Double radiusTo;
}
