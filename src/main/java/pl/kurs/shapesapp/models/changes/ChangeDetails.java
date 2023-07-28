package pl.kurs.shapesapp.models.changes;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeDetails {
    private String parameterName;
    private double previousValue;
    private double currentValue;
}
