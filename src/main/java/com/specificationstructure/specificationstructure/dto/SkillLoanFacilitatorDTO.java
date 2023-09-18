package com.specificationstructure.specificationstructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkillLoanFacilitatorDTO {

    private String  uid;
    @Size(max = 300)
    private String skillLoanFacilitatorName;
    private String cause;
    private String webUrl;
    private String logoUrl;
    private Integer amount;
    private String sector;
    private Double rateOfInterest;
    private SkillLoanFacilitatorAddressDTO address;
}
