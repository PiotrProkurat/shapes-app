package pl.kurs.shapesapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import pl.kurs.shapesapp.models.Shape;

public interface ShapeRepository extends JpaRepository<Shape, Long>, QuerydslPredicateExecutor<Shape> {

}
