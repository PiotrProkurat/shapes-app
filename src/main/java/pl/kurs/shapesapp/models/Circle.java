package pl.kurs.shapesapp.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;


@Entity
@DiscriminatorValue("3")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Circle extends Shape {
    private double radius;
}
