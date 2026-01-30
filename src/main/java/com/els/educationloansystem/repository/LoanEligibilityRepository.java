package com.els.educationloansystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.els.educationloansystem.entity.LoanEligibility;

@Repository
public interface LoanEligibilityRepository
        extends JpaRepository<LoanEligibility, Long> {
}
