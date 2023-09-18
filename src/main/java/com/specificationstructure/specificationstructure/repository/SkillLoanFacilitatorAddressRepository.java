package com.specificationstructure.specificationstructure.repository;

import com.specificationstructure.specificationstructure.entity.SkillLoanFacilitatorAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillLoanFacilitatorAddressRepository extends JpaRepository<SkillLoanFacilitatorAddress,Long> {
}
