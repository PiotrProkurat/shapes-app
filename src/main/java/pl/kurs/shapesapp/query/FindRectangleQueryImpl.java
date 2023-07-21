package pl.kurs.shapesapp.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.kurs.shapesapp.models.QRectangle;

import java.util.Map;
import java.util.Optional;


@Component
@Getter
@Setter
public class FindRectangleQueryImpl implements IFindShapesQuery{

    private final QRectangle qRectangle = new QRectangle("shape");

    @Override
    public BooleanBuilder toPredicate(Map<String, String> parameters) {
        BooleanBuilder conditions = new BooleanBuilder();
        NumberExpression<Double> area = qRectangle.height.multiply(qRectangle.width);
        NumberExpression<Double> perimeter = qRectangle.width.multiply(2).add(qRectangle.height.multiply(2));
        Optional.ofNullable(parameters.get("widthFrom")).map(x -> qRectangle.width.goe(Double.parseDouble(x))).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("widthTo")).map(x -> qRectangle.width.loe(Double.parseDouble(x))).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("heightFrom")).map(x -> qRectangle.height.goe(Double.parseDouble(x))).ifPresent(conditions::and);
        Optional.ofNullable(parameters.get("heightTo")).map(x -> qRectangle.height.loe(Double.parseDouble(x))).ifPresent(conditions::and);
        conditions.and(FindShapesQuery.compareAreaAndPerimeter(parameters, area, perimeter));
        conditions.and(qRectangle.type.containsIgnoreCase("Rectangle"));
        return conditions;
    }
}
