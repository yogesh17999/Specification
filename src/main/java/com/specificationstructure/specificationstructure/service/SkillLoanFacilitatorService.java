package com.specificationstructure.specificationstructure.service;

import com.specificationstructure.specificationstructure.advSearch.SearchDto;
import com.specificationstructure.specificationstructure.dto.ApiResponse;
import com.specificationstructure.specificationstructure.dto.FilterDTO;
import com.specificationstructure.specificationstructure.dto.SkillLoanFacilitatorDTO;
import com.specificationstructure.specificationstructure.dto.SkillLoanFacilitatorPagination;
import com.specificationstructure.specificationstructure.entity.SkillLoanFacilitator;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface SkillLoanFacilitatorService {

    SkillLoanFacilitator addSkillFacilitator(SkillLoanFacilitatorDTO skillLoanFacilitatorDTO);

    SkillLoanFacilitator getSkillFacilitatorById(String id);

    List<SkillLoanFacilitator> getSkillLoanFacilitator();

    ApiResponse deleteSkillLoanFacilitator(SkillLoanFacilitatorDTO skillLoanFacilitatorDTO);

    SkillLoanFacilitatorPagination getSkillLoanFacilitatorListing(Specification builder, Pageable pageable);

    SearchDto setSearchFilter(Optional<FilterDTO> filterDTO,Optional<String> operation);

}
