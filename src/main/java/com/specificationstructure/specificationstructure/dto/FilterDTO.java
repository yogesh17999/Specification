package com.specificationstructure.specificationstructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class FilterDTO {

    private List<String> skillLoanFacilitatorName;
    private List<String> cause;
    private List<String> amount;
    private List<String> sector;
    private List<String> state;
    private List<String> districts;
    private List<String> rateOfInterest;

    public Optional<List<String>> getSkillLoanFacilitatorName(){return Optional.ofNullable(skillLoanFacilitatorName);}
    public Optional<List<String>> getCause(){return Optional.ofNullable(cause);}
    public Optional<List<String>> getAmount(){return Optional.ofNullable(amount);}
    public Optional<List<String>> getSectors(){return Optional.ofNullable(sector);}
    public Optional<List<String>> getStates(){return Optional.ofNullable(state);}
    public Optional<List<String>> getDistricts(){return Optional.ofNullable(districts);}
    public Optional<List<String>> getRateOfInterest(){return Optional.ofNullable(rateOfInterest);}
}
