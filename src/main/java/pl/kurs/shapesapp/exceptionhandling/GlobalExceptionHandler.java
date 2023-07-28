package pl.kurs.shapesapp.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kurs.shapesapp.exceptions.NotFoundException;
import pl.kurs.shapesapp.exceptions.TheSameParametersException;
import pl.kurs.shapesapp.exceptions.UnknownShapeException;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundException(NotFoundException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                List.of(e.getMessage()),
                "NOT_FOUND",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnknownShapeException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundException(UnknownShapeException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                List.of(e.getMessage()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TheSameParametersException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundException(TheSameParametersException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                List.of(e.getMessage()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongEntityParametersException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundException(WrongEntityParametersException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                List.of(e.getMessage()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<String> fieldsErrorsMessages = e.getFieldErrors()
                .stream()
                .map(fieldError -> "field: " + fieldError.getField() + " / rejected value: '" + fieldError.getRejectedValue() + "' / message:  " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                fieldsErrorsMessages,
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }
}
