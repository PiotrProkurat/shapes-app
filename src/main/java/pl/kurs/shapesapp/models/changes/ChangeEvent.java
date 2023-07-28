package pl.kurs.shapesapp.models.changes;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ChangeEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Long idChangedShape;
    @ElementCollection
    List<ChangeDetails> changeDetails;
    private String changeAuthor;

    public ChangeEvent(LocalDateTime date, Long idChangedShape, List<ChangeDetails> changeDetails, String changeAuthor) {
        this.date = date;
        this.idChangedShape = idChangedShape;
        this.changeDetails = changeDetails;
        this.changeAuthor = changeAuthor;
    }
}
