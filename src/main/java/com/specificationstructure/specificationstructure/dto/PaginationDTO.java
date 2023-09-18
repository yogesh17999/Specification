package com.specificationstructure.specificationstructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {
    private long currentPageNumber;
    private long pageSize;
    private long totalElements = 0L;
}
