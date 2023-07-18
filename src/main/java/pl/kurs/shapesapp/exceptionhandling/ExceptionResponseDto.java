package pl.kurs.shapesapp.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ExceptionResponseDto {
    private List<String> errorsMessages;
    private String errorCode;
    private LocalDateTime timestamp;
}
