package com.els.educationloansystem.repository;

import com.els.educationloansystem.entity.LoanApplication;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanApplicationRepository
        extends JpaRepository<LoanApplication, Long> {

    long count();
    
    long countByStudentId(Long studentId);

    long countByStudentIdAndApplicationStatus(Long studentId, String status);


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
	
	List<LoanApplication> findByStudent_Id(Long studentId);

	List<LoanApplication> findByStudentId(Long id);
	
	Optional<LoanApplication> findByStudent_IdAndApplicationStatus(
	        Long studentId,
	        String applicationStatus
	);

	Optional<LoanApplication> findTopByStudent_IdOrderByApplicationDateDesc(Long id);
	
	boolean existsByStudent_IdAndApplicationStatus(Long studentId, String status);

}
