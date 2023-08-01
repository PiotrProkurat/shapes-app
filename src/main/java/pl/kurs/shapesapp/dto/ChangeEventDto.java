package pl.kurs.shapesapp.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeEventDto {
    private Long id;
    private LocalDateTime date;
    private Long idChangedShape;
    private List<ChangeDetailsDto> changeDetails;
    private String changeAuthor;
}
