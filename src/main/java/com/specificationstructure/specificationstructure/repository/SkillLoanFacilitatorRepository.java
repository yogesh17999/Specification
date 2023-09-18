package com.specificationstructure.specificationstructure.repository;

import com.specificationstructure.specificationstructure.entity.SkillLoanFacilitator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillLoanFacilitatorRepository extends JpaRepository<SkillLoanFacilitator, String> , JpaSpecificationExecutor<SkillLoanFacilitator> {
}
