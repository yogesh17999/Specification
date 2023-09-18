package com.specificationstructure.specificationstructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillLoanFacilitator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private String uid;

    @Size(max = 300)
    @Column(name = "skill_loan_facilitator_name", length = 300)
    private String skillLoanFacilitatorName;

    private String cause;

    @Column(name = "web_url")
    private String webUrl;

    @Column(name = "logo_url")
    private String logoUrl;

    private Integer amount;

    private String sector;

    @Column(name = "rate_of_interest")
    private Double rateOfInterest;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = SkillLoanFacilitatorAddress.class)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private SkillLoanFacilitatorAddress address;
}