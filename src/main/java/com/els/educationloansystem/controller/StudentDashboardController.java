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

        long total = loanApplicationRepository.countByStudentId(studentId);
        long approved = loanApplicationRepository
                .countByStudentIdAndApplicationStatus(studentId, "APPROVED");
        long pending = loanApplicationRepository
                .countByStudentIdAndApplicationStatus(studentId, "PENDING");
        long rejected = loanApplicationRepository
                .countByStudentIdAndApplicationStatus(studentId, "REJECTED");

        return new StudentLoanStatusSummaryDto(
                total,
                approved,
                pending,
                rejected
        );
    }
}
