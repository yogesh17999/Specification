package com.specificationstructure.specificationstructure.advSearch;


import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

public class QueryHelper {

public From getFrom(SearchCriteria searchCriteria, Root root){

    switch(searchCriteria.getField())
    {
        case "state":
            return addressJoin(root);
        case "districts":
            return addressJoin(root);
        default:
            return root;
    }
}

    private Join<?, ?> addressJoin(Root root) {
        return root.join("address");
    }
}
