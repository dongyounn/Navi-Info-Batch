package com.juso.batch.config.querydsl.options;


import com.juso.batch.config.querydsl.expression.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;

import javax.annotation.Nonnull;
import java.util.List;

public class QuerydslNoOffsetStringOptions<T> extends QuerydslNoOffsetOptions<T> {

    private String currentId;

    private final StringPath field;

    public QuerydslNoOffsetStringOptions(@Nonnull StringPath field,
                                         @Nonnull Expression expression) {
        super(field, expression);
        this.field = field;
    }

    @Override
    public void initFirstId(JPAQuery<T> query, int page) {
        if (page == 0) {
            List<String> fetch = query
                    .select(selectFirstId())
                    .fetch();
            int size = fetch.size();
            if (size > 0) {
                int index = expression.isAsc() ? 0 : size - 1;
                currentId = fetch.get(index);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("First Select Key= " + currentId);
            }
        }
    }

    @Override
    public JPAQuery<T> createQuery(JPAQuery<T> query, int page) {
        if (currentId == null) {
            return query;
        }

        return query
                .where(whereExpression(page))
                .orderBy(orderExpression());
    }

    private BooleanExpression whereExpression(int page) {
        return expression.where(field, page, currentId);
    }

    private OrderSpecifier<String> orderExpression() {
        return expression.order(field);
    }

    @Override
    public void resetCurrentId(T item) {
        currentId = (String) getFiledValue(item);
        if (logger.isDebugEnabled()) {
            logger.debug("Current Select Key= " + currentId);
        }
    }

    private StringExpression selectFirstId() {
        if (expression.isAsc()) {
            return field.min();
        }

        return field.max();
    }
}
