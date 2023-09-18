package com.specificationstructure.specificationstructure.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.specificationstructure.specificationstructure.advSearch.SearchCriteria;
import com.specificationstructure.specificationstructure.advSearch.SearchDto;
import com.specificationstructure.specificationstructure.constants.Constants;
import com.specificationstructure.specificationstructure.dto.*;
import com.specificationstructure.specificationstructure.entity.SkillLoanFacilitator;
import com.specificationstructure.specificationstructure.repository.SkillLoanFacilitatorRepository;
import com.specificationstructure.specificationstructure.service.SkillLoanFacilitatorService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class SkillLoanFacilitatorServiceImpl implements SkillLoanFacilitatorService {

    private  SkillLoanFacilitatorRepository skillLoanFacilitatorRepository;

    @Autowired
    SkillLoanFacilitatorServiceImpl(SkillLoanFacilitatorRepository skillLoanFacilitatorRepository)
    {
        this.skillLoanFacilitatorRepository = skillLoanFacilitatorRepository;
    }

    @Override
    public SkillLoanFacilitator addSkillFacilitator(SkillLoanFacilitatorDTO skillLoanFacilitatorDTO) {
        log.info("SkillLoanFacilitatorServiceImpl :: addSkillFacilitator");
        validateRequest(skillLoanFacilitatorDTO);
        return skillLoanFacilitatorRepository.save(new ObjectMapper().convertValue(skillLoanFacilitatorDTO, SkillLoanFacilitator.class));
    }

    @Override
    public SkillLoanFacilitator getSkillFacilitatorById(String id) {
        log.info("SkillLoanFacilitatorServiceImpl :: getSkillFacilitatorById");
        return skillLoanFacilitatorRepository.findById(id).orElseThrow(() -> new NotFoundException("Given id does not exist: "+id));
    }

    @Override
    public List<SkillLoanFacilitator> getSkillLoanFacilitator() {
        log.info("getSkillFacilitatorById :: getSkillLoanFacilitator");
        return skillLoanFacilitatorRepository.findAll();
    }

    @Override
    public ApiResponse deleteSkillLoanFacilitator(SkillLoanFacilitatorDTO skillLoanFacilitatorDTO) {
        skillLoanFacilitatorRepository.deleteById(skillLoanFacilitatorDTO.getUid());
        return ApiResponse.builder().status(HttpStatus.OK).message("Successfully deleted").build();
    }

    @Override
    public SkillLoanFacilitatorPagination getSkillLoanFacilitatorListing(Specification specification, Pageable pageable) {
        Page<SkillLoanFacilitator> skillLoanFacilitators = skillLoanFacilitatorRepository.findAll(specification, pageable);
        SkillLoanFacilitatorPagination skillLoanFacilitatorPagination = new SkillLoanFacilitatorPagination();
        List<SkillLoanFacilitatorDTO> skillLoanFacilitatorDTOS = skillLoanFacilitators.stream().map(
                skillLoanFacilitator ->
                        new ObjectMapper().convertValue(skillLoanFacilitator, SkillLoanFacilitatorDTO.class)
        ).collect(Collectors.toList());

        skillLoanFacilitatorPagination.setSkillLoanFacilitatorDTOList(skillLoanFacilitatorDTOS);
        skillLoanFacilitatorPagination.setPaginationDTO(new PaginationDTO((skillLoanFacilitators.getPageable().getPageNumber() + 1),pageable.getPageSize(), skillLoanFacilitators.getTotalElements()));
        return skillLoanFacilitatorPagination;
    }

    @Override
    public SearchDto setSearchFilter(Optional<FilterDTO> filterDTO,Optional<String> operation) {
        List<SearchCriteria> searchCriteria = new ArrayList<>();
        filterDTO.ifPresent(
                filterFields -> {
                    filterFields.getSkillLoanFacilitatorName().ifPresent(skillLoanFacilitatorName ->  searchCriteria.add(addFilter("skillLoanFacilitatorName",skillLoanFacilitatorName,operation.orElse("eq"))));
                    filterFields.getAmount().ifPresent(amount ->searchCriteria.add(addFilter("amount",amount,"eq")));
                    filterFields.getCause().ifPresent(cause ->searchCriteria.add(addFilter("cause",cause,operation.orElse("eq"))));
                    filterFields.getSectors().ifPresent(cause ->searchCriteria.add(addFilter("cause",cause,operation.orElse("eq"))));
                    filterFields.getDistricts().ifPresent(districts ->searchCriteria.add(addFilter("districts",districts,operation.orElse("eq"))));
                    filterFields.getStates().ifPresent(states ->searchCriteria.add(addFilter("state",states,operation.orElse("eq"))));
                    filterFields.getRateOfInterest().ifPresent(rateOfInterest ->searchCriteria.add(addFilter("rateOfInterest",rateOfInterest,"eq")));
                });
        return new SearchDto(searchCriteria, "All");
    }

    private SearchCriteria addFilter(String field, List<String> value, String operation) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setField(field);
        searchCriteria.setOperation(operation);
        searchCriteria.setValues(value);
        searchCriteria.setFilterOption("All");
        return searchCriteria;
    }

    private void validateRequest(SkillLoanFacilitatorDTO skillLoanFacilitatorDTO) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
        if (pattern.matcher(skillLoanFacilitatorDTO.getSkillLoanFacilitatorName()).find()) throw new BadRequestException("Special character not allowed!!");
        else if (!isValidAmount(skillLoanFacilitatorDTO.getAmount())) throw new BadRequestException("Invalid Amount!!");
        else if (!isValidRateOfIntrest(skillLoanFacilitatorDTO.getRateOfInterest()))  throw new BadRequestException("Invalid rate of intrest!!");
    }

    private boolean isValidRateOfIntrest(Double rateOfInterest) {
        return rateOfInterest >= Constants.MIN_AMOUNT && rateOfInterest <= Constants.MAX_RATE_OF_INTEREST;
    }

    private boolean isValidAmount(Integer amount) {
        return amount >= Constants.MIN_AMOUNT && amount <= Constants.MAX_AMOUNT;
    }
}
