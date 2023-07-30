package pl.kurs.shapesapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.shapesapp.exceptions.WrongEntityParametersException;
import pl.kurs.shapesapp.models.changes.ChangeEvent;
import pl.kurs.shapesapp.repositories.ChangeEventRepository;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChangeEventService {
    private final ChangeEventRepository changeEventRepository;

    public ChangeEvent create(ChangeEvent changeEvent) {
        return changeEventRepository.save(
                Optional.ofNullable(changeEvent)
                        .filter(x -> Objects.isNull(x.getId()))
                        .orElseThrow(() -> new WrongEntityParametersException("Wrong entity for persist!")));
    }

    public Page<ChangeEvent> getAllChangesShapeWithId(Pageable pageable, Long id) {
        return changeEventRepository.findAllByIdChangedShapeIs(pageable, id);
    }
}
