package pl.kurs.shapesapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("3")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Circle extends Shape{
    private double radius;
}
