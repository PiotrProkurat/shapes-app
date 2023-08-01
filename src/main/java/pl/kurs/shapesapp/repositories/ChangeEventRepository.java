package pl.kurs.shapesapp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.shapesapp.models.changes.ChangeEvent;

import java.util.List;

public interface ChangeEventRepository extends JpaRepository<ChangeEvent, Long> {
    Page<ChangeEvent> findAllByIdChangedShapeIs(Pageable pageable, Long id);
}
