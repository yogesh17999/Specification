package com.specificationstructure.specificationstructure.advSearch;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchDto {

    private List<SearchCriteria> searchCriteria;
    private String filterOption;
}
