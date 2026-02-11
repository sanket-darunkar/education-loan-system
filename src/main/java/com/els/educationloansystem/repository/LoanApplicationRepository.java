package com.els.educationloansystem.repository;

import com.els.educationloansystem.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanApplicationRepository
        extends JpaRepository<LoanApplication, Long> {

    /* ================= BASIC COUNTS ================= */

    long count();

    long countByApplicationStatus(String applicationStatus);

    long countByStudent_Id(Long studentId);

    long countByStudent_IdAndApplicationStatus(Long studentId, String status);

    /* ================= STUDENT ================= */

    List<LoanApplication> findByStudent_Id(Long studentId);

    Optional<LoanApplication> findByStudent_IdAndApplicationStatus(
            Long studentId,
            String applicationStatus
    );

    Optional<LoanApplication> findTopByStudent_IdOrderByApplicationDateDesc(
            Long studentId
    );

    boolean existsByStudent_IdAndApplicationStatus(
            Long studentId,
            String status
    );

    /* ================= ADMIN ================= */

    List<LoanApplication> findByApplicationStatus(String applicationStatus);

    /* ================= DASHBOARD TREND ================= */

    @Query("""
        SELECT la.applicationDate, COUNT(la)
        FROM LoanApplication la
        GROUP BY la.applicationDate
        ORDER BY la.applicationDate
    """)
    List<Object[]> countApplicationsGroupedByDate();
}
