package pl.kurs.shapesapp.query;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.kurs.shapesapp.models.QShape;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Getter
@Setter
public class FindShapesQuery {

    private final Set<IFindShapesQuery> shapesQuery;

    public FindShapesQuery(Set<IFindShapesQuery> allShapesQuery) {
        shapesQuery = allShapesQuery;
    }

    public Predicate toPredicate(Map<String, String> parameters) {
        BooleanBuilder conditions = new BooleanBuilder();
        for (IFindShapesQuery shapeImplementation : shapesQuery) {
            conditions.or(shapeImplementation.toPredicate(parameters));
        }
        Optional.ofNullable(parameters.get("type")).map(QShape.shape.type::containsIgnoreCase).ifPresent(conditions::and);
        return conditions;
    }

    public static void checkAreaAndPerimeter(Map<String, String> parameters, BooleanBuilder conditions, NumberExpression<Double> area, NumberExpression<Double> perimeter, BooleanExpression correctShapeType) {
        Optional.ofNullable(parameters.get("areaFrom")).map(x -> area.goe(Double.parseDouble(x)).and(correctShapeType)).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("areaTo")).map(x -> area.loe(Double.parseDouble(x)).and(correctShapeType)).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("perimeterFrom")).map(x -> perimeter.goe(Double.parseDouble(x)).and(correctShapeType)).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("perimeterTo")).map(x -> perimeter.loe(Double.parseDouble(x)).and(correctShapeType)).ifPresent(conditions::and);
    }
}
