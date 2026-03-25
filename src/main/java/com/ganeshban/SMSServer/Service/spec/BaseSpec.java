package com.ganeshban.smsserver.service.spec;

import com.ganeshban.smsserver.config.SystemException;
import com.ganeshban.smsserver.model.search.SearchCriteria;
import com.ganeshban.smsserver.model.search.SearchRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import org.hibernate.query.sqm.PathElementException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BaseSpec<T> {

    public Specification<T> buildCriteria(SearchRequest request) {
        return new Specification<>() {
            @Serial
            private static final long serialVersionUID = 1934343947832L;

            @Override
            public @NotNull Predicate toPredicate(@NotNull Root<T> root, CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
                return builder.and(generatePredicate(request, root, query, builder).toArray(new Predicate[0]));
            }
        };
    }

    public List<Predicate> generatePredicate(SearchRequest request, From root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        List<SearchCriteria> filters = request.getFilters();

        if (!CollectionUtils.isEmpty(filters)) {
            for (var filter : filters) {
                Predicate predicate = buildFilter(filter, root, query, builder);
                if (Objects.nonNull(predicate)) {
                    predicates.add(predicate);
                }
            }
        }
        return predicates;
    }

    public Predicate buildFilter(SearchCriteria criteria, From root, CriteriaQuery query, CriteriaBuilder builder) throws SystemException {
        validateCriteria(root, criteria.getKey());
        criteriaType criteriaType = getCriteriaType(root, criteria.getKey());
        String value = criteria.getValue().trim().toLowerCase();
        boolean isNumber = criteriaType == BaseSpec.criteriaType.NUMBER;
        boolean isDate = criteriaType == BaseSpec.criteriaType.DATETIME;

        return switch (criteria.getOp()) {
            case EQ -> buildEqual(builder, root, isDate, isNumber, criteria.getKey(), value);
            case NEQ -> buildNotEqual(builder, root, isDate, isNumber, criteria.getKey(), value);
            case LT -> buildLessThen(builder, root, isDate, isNumber, criteria.getKey(), value);
            case LE -> null;
            case GT -> null;
            case GE -> null;
            case IN -> null;
            case LIKE -> null;
            case NOT_LIKE -> null;
            case NULL -> null;
            case NOT_NULL -> null;
            case START_WITH -> null;
            case END_WITH -> null;
        };
    }

    private Predicate buildEqual(CriteriaBuilder builder, From root, boolean isDate, boolean isNumber, String key, String value) {
        return builder.equal(root.get(key), value);
    }

    private Predicate buildNotEqual(CriteriaBuilder builder, From root, boolean isDate, boolean isNumber, String key, String value) {
        return builder.notEqual(root.get(key), value);
    }

    private Predicate buildLessThen(CriteriaBuilder builder, From root, boolean isDate, boolean isNumber, String key, String value) {
        return builder.lessThan(root.get(key), value);
    }


    private void validateCriteria(From root, String key) {
        try {
            root.get(key);
        } catch (PathElementException e) {
            throw new SystemException("Invalid criteria key " + key);
        }
    }

    private enum criteriaType {DATETIME, NUMBER, STRING}

    private criteriaType getCriteriaType(From root, String key) throws SystemException {
        Class<?> javaType = root.get(key).getJavaType();

        if (javaType.isAssignableFrom(Date.class)) {
            return criteriaType.DATETIME;
        }

        if (javaType.isAssignableFrom(Number.class)) {
            return criteriaType.NUMBER;
        }

        return criteriaType.STRING;

    }


}
