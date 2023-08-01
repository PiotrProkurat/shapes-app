package pl.kurs.shapesapp.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeDetailsDto {
    private String parameterName;
    private double previousValue;
    private double currentValue;
}
