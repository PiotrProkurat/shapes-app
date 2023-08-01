package pl.kurs.shapesapp.models.changes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "change_details")
public class ChangeDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parameterName;
    private double previousValue;
    private double currentValue;
    @ManyToOne
    @JoinColumn(name = "changeEvent_id")
    private ChangeEvent changeEvent;

    public ChangeDetails(String parameterName, double previousValue, double currentValue) {
        this.parameterName = parameterName;
        this.previousValue = previousValue;
        this.currentValue = currentValue;
    }
}
