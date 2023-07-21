package pl.kurs.shapesapp.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.kurs.shapesapp.models.QSquare;

import java.util.Map;
import java.util.Optional;

@Component
@Getter
@Setter
public class FindSquareQueryImpl implements IFindShapesQuery {

    private final QSquare qSquare = new QSquare("shape");

    @Override
    public BooleanBuilder toPredicate(Map<String, String> parameters) {
        BooleanBuilder conditions = new BooleanBuilder();
        NumberExpression<Double> area = qSquare.width.multiply(qSquare.width);
        NumberExpression<Double> perimeter = qSquare.width.multiply(4);
        Optional.ofNullable(parameters.get("widthFrom")).map(x -> qSquare.width.goe(Double.parseDouble(x))).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("widthTo")).map(x -> qSquare.width.loe(Double.parseDouble(x))).ifPresent(conditions::and);
        conditions.and(FindShapesQuery.compareAreaAndPerimeter(parameters, area, perimeter));
        conditions.and(qSquare.type.containsIgnoreCase("Square"));
        return conditions;
    }
}
