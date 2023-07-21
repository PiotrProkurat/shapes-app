package pl.kurs.shapesapp.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.kurs.shapesapp.models.QCircle;

import java.util.Map;
import java.util.Optional;

@Component
@Getter
@Setter
public class FindCircleQueryImpl implements IFindShapesQuery{

    private final QCircle qCircle = new QCircle("shape");

    @Override
    public BooleanBuilder toPredicate(Map<String, String> parameters) {
        BooleanBuilder conditions = new BooleanBuilder();
        NumberExpression<Double> area = (qCircle.radius.multiply(qCircle.radius)).multiply(Math.PI);
        NumberExpression<Double> perimeter = qCircle.radius.multiply(2 * Math.PI);
        Optional.ofNullable(parameters.get("radiusFrom")).map(x -> qCircle.radius.goe(Double.parseDouble(x))).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("radiusTo")).map(x -> qCircle.radius.loe(Double.parseDouble(x))).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("areaFrom")).map(x -> (area).goe(Double.parseDouble(x)).and(qCircle.type.containsIgnoreCase("circle"))).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("areaTo")).map(x -> (area).loe(Double.parseDouble(x)).and(qCircle.type.containsIgnoreCase("circle"))).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("perimeterFrom")).map(x -> (perimeter).goe(Double.parseDouble(x)).and(qCircle.type.containsIgnoreCase("circle"))).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("perimeterTo")).map(x -> (perimeter).loe(Double.parseDouble(x)).and(qCircle.type.containsIgnoreCase("circle"))).ifPresent(conditions::and);
        return conditions;
    }



}
