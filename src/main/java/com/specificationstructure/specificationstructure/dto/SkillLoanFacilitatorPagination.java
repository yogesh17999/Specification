package com.specificationstructure.specificationstructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillLoanFacilitatorPagination {
    private PaginationDTO paginationDTO;
    private List<SkillLoanFacilitatorDTO> skillLoanFacilitatorDTOList;
}
