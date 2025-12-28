package com.els.educationloansystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.els.educationloansystem.entity.LoanEligibility;

public interface LoanRepository extends JpaRepository<LoanEligibility, Long> {

}
