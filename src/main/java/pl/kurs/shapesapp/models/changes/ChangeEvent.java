package pl.kurs.shapesapp.models.changes;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class ChangeEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @LastModifiedDate
    private LocalDateTime date;
    private Long idChangedShape;
    @ElementCollection
    List<ChangeDetails> changeDetails;
    @LastModifiedBy
    private String changeAuthor;

    public ChangeEvent(Long idChangedShape, List<ChangeDetails> changeDetails) {
        this.idChangedShape = idChangedShape;
        this.changeDetails = changeDetails;
    }
}
