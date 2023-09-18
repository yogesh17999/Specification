package com.specificationstructure.specificationstructure.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillLoanFacilitatorAddress {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    @Column(name ="district")
    private String district;
    @Column(name ="state")
    private String state;
    private Double latitude;
    private Double longitude;
}
