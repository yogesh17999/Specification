package com.specificationstructure.specificationstructure.advSearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String field;
    private List<String> values;
    private String operation;
    private String filterOption;

    public SearchCriteria(String field, String operation, List<String> values){
        this.field=field;
        this.operation=operation;
        this.values=values;
    }
}
