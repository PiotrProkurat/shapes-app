package pl.kurs.shapesapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rectangle extends Shape{
    private double width;
    private double height;
}
