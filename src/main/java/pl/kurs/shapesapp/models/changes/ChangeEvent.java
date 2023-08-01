package pl.kurs.shapesapp.models.changes;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
@Table(name = "change_events")
public class ChangeEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @LastModifiedDate
    private LocalDateTime date;
    private Long idChangedShape;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "changeEvent", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<ChangeDetails> changeDetails = new ArrayList<>();
    @LastModifiedBy
    private String changeAuthor;

    public ChangeEvent(Long idChangedShape) {
        this.idChangedShape = idChangedShape;
    }

    public void addChangeDetails(List<ChangeDetails> changeDetails){
        this.changeDetails.addAll(changeDetails);
        changeDetails.forEach(x -> x.setChangeEvent(this));
    }
}
