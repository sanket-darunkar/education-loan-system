package com.els.educationloansystem.repository;

import com.els.educationloansystem.entity.LoanApplication;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository
        extends JpaRepository<LoanApplication, Long> {

    long count();

    long countByApplicationStatus(String applicationStatus);

    // ✅ REQUIRED for trend chart
    @Query("""
        SELECT la.applicationDate, COUNT(la)
        FROM LoanApplication la
        GROUP BY la.applicationDate
        ORDER BY la.applicationDate
    """)
    List<Object[]> countApplicationsGroupedByDate();

	@Nullable
	Object findByApplicationStatus(String string);
}
