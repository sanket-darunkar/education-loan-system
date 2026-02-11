package com.els.educationloansystem.controller;

import com.els.educationloansystem.dto.StudentLoanStatusSummaryDto;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.repository.LoanApplicationRepository;
import com.els.educationloansystem.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/dashboard")
@CrossOrigin("*")
public class StudentDashboardController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @GetMapping("/loan-status-summary")
    public StudentLoanStatusSummaryDto getLoanStatusSummary(Authentication authentication) {

        String email = authentication.getName();
        Student student = studentRepository.findByEmail(email)
                .orElseThrow();

        Long studentId = student.getId();

        long total = loanApplicationRepository.countByStudent_Id(studentId);
        long approved = loanApplicationRepository
                .countByStudent_IdAndApplicationStatus(studentId, "APPROVED");
        long pending = loanApplicationRepository
                .countByStudent_IdAndApplicationStatus(studentId, "PENDING");
        long rejected = loanApplicationRepository
                .countByStudent_IdAndApplicationStatus(studentId, "REJECTED");

        return new StudentLoanStatusSummaryDto(
                total,
                approved,
                pending,
                rejected
        );
    }
}
