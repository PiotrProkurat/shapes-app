package pl.kurs.shapesapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="shape_type", discriminatorType = DiscriminatorType.INTEGER)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @Version
    private int version;
}
