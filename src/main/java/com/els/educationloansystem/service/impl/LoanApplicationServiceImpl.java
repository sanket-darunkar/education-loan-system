package com.els.educationloansystem.service.impl;

import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.els.educationloansystem.dto.LoanApplicationRequest;
import com.els.educationloansystem.dto.LoanApplicationResponse;
import com.els.educationloansystem.entity.Loan;
import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.repository.LoanApplicationRepository;
import com.els.educationloansystem.repository.LoanRepository;
import com.els.educationloansystem.repository.StudentRepository;
import com.els.educationloansystem.service.LoanApplicationService;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanRepository loanRepo;

    // ================= ADMIN =================

    @Override
    public void approveLoan(Long applicationId) {

        LoanApplication application = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setApplicationStatus("APPROVED");

        if (!loanRepo.existsByApplication_ApplicationId(applicationId)) {
            Loan loan = new Loan();
            loan.setApplication(application);
            loan.setInterestRate(8.0);
            loan.setTenure(5);
            loan.setApprovedDate(LocalDate.now());
            loan.setLoanStatus("ACTIVE");
            loanRepo.save(loan);
        }

        loanApplicationRepository.save(application);
    }

    @Override
    public void rejectLoan(Long applicationId, String reason) {

        LoanApplication application = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setApplicationStatus("REJECTED");
        application.setEligibilityStatus(reason);

        loanApplicationRepository.save(application);
    }

    // ================= STUDENT =================

    @Override
    public LoanApplicationResponse applyLoan(LoanApplicationRequest request) {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (loanApplicationRepository.existsByStudent_IdAndApplicationStatus(
                student.getId(), "PENDING")) {
            throw new RuntimeException("You already have a pending loan application");
        }

        LoanApplication app = new LoanApplication();
        app.setStudent(student);
        app.setLoanAmount(request.getLoanAmount());
        app.setCourseName(request.getCourseName());
        app.setInstituteName(request.getInstituteName());
        app.setCourseDuration(request.getCourseDuration());
        app.setApplicationStatus("PENDING");
        app.setEligibilityStatus("PENDING");

        // ✅ CORRECT REPOSITORY
        LoanApplication saved = loanApplicationRepository.save(app);

        return new LoanApplicationResponse(
                saved.getApplicationId(),
                saved.getLoanAmount(),
                saved.getCourseName(),
                saved.getInstituteName(),
                saved.getCourseDuration(),
                saved.getApplicationStatus(),
                saved.getEligibilityStatus(),
                saved.getApplicationDate()
        );
    }

    @Override
    public Object getMyApplication(String email) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return loanApplicationRepository.findByStudent_Id(student.getId());
    }

    @Override
    public LoanApplication getMyLatestApplication(Authentication authentication) {

        Student student = studentRepository
                .findByEmail(authentication.getName())
                .orElseThrow();

        return loanApplicationRepository
                .findTopByStudent_IdOrderByApplicationDateDesc(student.getId())
                .orElse(null);
    }

    @Override
    public Object getAllApplicationsForAdmin() {
        return loanApplicationRepository.findAll();
    }
}
