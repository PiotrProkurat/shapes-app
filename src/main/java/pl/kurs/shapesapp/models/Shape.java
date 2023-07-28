package pl.kurs.shapesapp.models;

import jakarta.persistence.*;
import lombok.*;



import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="shape_type", discriminatorType = DiscriminatorType.INTEGER)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "shapes")
public class Shape implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @Version
    private int version;
    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;
}
