package pl.kurs.shapesapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.kurs.shapesapp.models.Shape;

import java.util.List;

public interface ShapeRepository extends JpaRepository<Shape, Long> {
    List<Shape> searchShapes(@Param("criteria") ShapeSearchCriteria criteria);
}
