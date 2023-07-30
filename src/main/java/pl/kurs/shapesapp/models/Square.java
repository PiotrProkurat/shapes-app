package pl.kurs.shapesapp.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;


@Entity
@DiscriminatorValue("1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Square extends Shape {
    private double width;
}
