package pl.kurs.shapesapp.dto;

import lombok.Getter;
import lombok.Setter;
import pl.kurs.shapesapp.models.changes.ChangeDetails;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ChangeEventDto {
    private Long id;
    private LocalDateTime date;
    private Long idChangedShape;
    List<ChangeDetails> changeDetails;
    private String changeAuthor;
}
