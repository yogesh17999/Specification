package com.specificationstructure.specificationstructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.specificationstructure.specificationstructure.advSearch.SearchDto;
import com.specificationstructure.specificationstructure.advSearch.SpecificationBuilder;
import com.specificationstructure.specificationstructure.dto.FilterDTO;
import com.specificationstructure.specificationstructure.dto.SkillLoanFacilitatorDTO;
import com.specificationstructure.specificationstructure.dto.SkillLoanFacilitatorPagination;
import com.specificationstructure.specificationstructure.service.SkillLoanFacilitatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
@Slf4j
public class SkillLoanFacilitatorController {

    @Autowired
    SkillLoanFacilitatorService skillLoanFacilitatorService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public SkillLoanFacilitatorDTO addSkillLoanFacilitator(@RequestBody SkillLoanFacilitatorDTO skillLoanFacilitatorDTO){
        return objectMapper.convertValue(skillLoanFacilitatorService.addSkillFacilitator(skillLoanFacilitatorDTO),SkillLoanFacilitatorDTO.class);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public SkillLoanFacilitatorDTO getSkillLoanFacilitatorById(@PathVariable String id) {
        return objectMapper.convertValue(skillLoanFacilitatorService.getSkillFacilitatorById(id),SkillLoanFacilitatorDTO.class);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<SkillLoanFacilitatorDTO> getAll() {
        List<SkillLoanFacilitatorDTO> skillLoanFacilitatorDTOS = new ArrayList<>();
                skillLoanFacilitatorService.getSkillLoanFacilitator().stream().forEach(
                skillLoanFacilitator -> skillLoanFacilitatorDTOS.add( objectMapper.convertValue(skillLoanFacilitator,SkillLoanFacilitatorDTO.class)));
         return skillLoanFacilitatorDTOS;
    }

    @PostMapping("/search")
    public SkillLoanFacilitatorPagination searchSkillLoanFacilitator(Optional<FilterDTO> filterDTO,
                                                                     @RequestParam(defaultValue = "skillLoanFacilitatorName") String sortBy,
                                                                     @RequestParam(defaultValue = "0") int currentPage,
                                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                                     @RequestParam(defaultValue = "1") int sortSet,
                                                                     @RequestParam(defaultValue = "eq") Optional<String> operation) {
        SearchDto searchDto = skillLoanFacilitatorService.setSearchFilter(filterDTO,operation);
        Pageable pageable = PageRequest.of(currentPage,pageSize, sortSet == 1 ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        return skillLoanFacilitatorService.getSkillLoanFacilitatorListing(new SpecificationBuilder().builder(searchDto.getSearchCriteria()),pageable);
    }
}
