package com.specificationstructure.specificationstructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class SkillLoanFacilitatorAddressDTO {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String district;
    private String state;
    private Double latitude;
    private Double longitude;
}
