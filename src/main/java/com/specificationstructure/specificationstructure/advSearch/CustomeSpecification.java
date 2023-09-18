package com.specificationstructure.specificationstructure.advSearch;

import com.specificationstructure.specificationstructure.enumerations.SearchOperation;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CustomeSpecification implements org.springframework.data.jpa.domain.Specification {

    private final SearchCriteria searchCriteria;

    public CustomeSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        QueryHelper queryHelp = new QueryHelper();
        log.info(searchCriteria.getValues().toString());
        if(searchCriteria.getField().equalsIgnoreCase("amount")) {
            List<Integer> parseList = searchCriteria.getValues().stream().map(value -> Integer.parseInt(value)).collect(Collectors.toList());
            log.info(parseList.toString());

            return criteriaBuilder.in(queryHelp.getFrom(searchCriteria,root).<String>get(searchCriteria.getField())).value(parseList);
        }
        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation())))
        {
            case EQUAL:
                return criteriaBuilder.in(queryHelp.getFrom(searchCriteria,root).get(searchCriteria.getField())).value(searchCriteria.getValues());
            case CONTAINS:
                  return criteriaBuilder.like(queryHelp.getFrom(searchCriteria,root).get(searchCriteria.getField()),"%" + searchCriteria.getValues().get(0)+"%");
            default:
                return null;
        }
    }
}
