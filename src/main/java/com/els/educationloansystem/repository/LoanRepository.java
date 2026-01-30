package com.els.educationloansystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.els.educationloansystem.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    boolean existsByApplication_ApplicationId(Long applicationId);
}
