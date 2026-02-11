package com.els.educationloansystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.els.educationloansystem.dto.StudentDashboardSummaryDto;
import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.repository.LoanApplicationRepository;

@Service
public class StudentDashboardService {

    @Autowired
    private LoanApplicationRepository loanRepo;

    public StudentDashboardSummaryDto getDashboardSummary(Long studentId) {

        List<LoanApplication> loans =
                loanRepo.findAll()
                        .stream()
                        .filter(l -> l.getStudent().getId().equals(studentId))
                        .toList();

        long totalLoans = loans.size();

        List<LoanApplication> approvedLoansList =
                loans.stream()
                        .filter(l -> "APPROVED".equalsIgnoreCase(l.getApplicationStatus()))
                        .toList();

        long approvedLoans = approvedLoansList.size();

        double outstandingBalance =
                approvedLoansList.stream()
                        .mapToDouble(LoanApplication::getLoanAmount)
                        .sum();

        double upcomingEmi =
                approvedLoansList.stream()
                        .mapToDouble(l ->
                                l.getLoanAmount() /
                                (l.getCourseDuration() * 12)
                        ).sum();

        // 🔥 Simple realistic credit score logic
        int creditScore = 650 + (int) (approvedLoans * 30);

        return new StudentDashboardSummaryDto(
                totalLoans,
                approvedLoans,
                outstandingBalance,
                Math.round(upcomingEmi),
                Math.min(creditScore, 850)
        );
    }
}
