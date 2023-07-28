package pl.kurs.shapesapp.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;



@Entity
@DiscriminatorValue("2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Rectangle extends Shape{
    private double width;
    private double height;
}
