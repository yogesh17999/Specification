package com.specificationstructure.specificationstructure.advSearch;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Data
public class SpecificationBuilder {

    //combine all the searchCriteria
    public Specification builder(List<SearchCriteria> searchCriteria) {
        if(ObjectUtils.isEmpty(searchCriteria))
            return null;

        Specification result= null;
        for (SearchCriteria criteria : searchCriteria) {
            result = criteria.getFilterOption().equalsIgnoreCase("ALL")
                    ? Specification.where(result).and(new CustomeSpecification(criteria))
                    : Specification.where(result).or(new CustomeSpecification(criteria));
        }
        return result;
    }
}
