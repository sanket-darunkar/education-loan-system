package com.els.educationloansystem.repository;

import com.els.educationloansystem.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

}
