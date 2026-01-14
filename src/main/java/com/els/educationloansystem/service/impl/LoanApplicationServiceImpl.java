package com.els.educationloansystem.service.impl;

import java.time.LocalDate;

import com.els.educationloansystem.dto.LoanApplicationRequest;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.els.educationloansystem.entity.Loan;
import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.repository.LoanApplicationRepository;
import com.els.educationloansystem.repository.LoanRepository;
import com.els.educationloansystem.service.LoanApplicationService;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoanApplicationRepository applicationRepo;

    @Autowired
    private LoanRepository loanRepo;

    @Override
    public void approveLoan(Long applicationId) {

        LoanApplication application = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setApplicationStatus("APPROVED");

        Loan loan = new Loan();
        loan.setApplication(application);
        loan.setInterestRate(8.0);
        loan.setTenure(5);
        loan.setApprovedDate(LocalDate.now());
        loan.setLoanStatus("ACTIVE");

        loanRepo.save(loan);
        applicationRepo.save(application);
    }

    @Override
    public void rejectLoan(Long applicationId, String reason) {



        LoanApplication application = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setApplicationStatus("REJECTED");
        application.setEligibilityStatus(reason);

        applicationRepo.save(application);
    }

    @Override
    public LoanApplication applyLoan(LoanApplicationRequest request) {

        Student student = studentRepository.findById(request.getStudentId()).
                orElseThrow(() -> new RuntimeException("Student Not Found"));

        LoanApplication application = new LoanApplication();

        application.setStudent(student);
        application.setLoanAmount(request.getLoanAmount());
        application.setCourseName(request.getCourseName());
        application.setInstituteName(request.getInstituteName());
        application.setCourseDuration(request.getCourseDuration());
        application.setApplicationDate(LocalDate.now());

        application.setApplicationStatus("PENDING");
        application.setEligibilityStatus("PENDING");

        return applicationRepo.save(application);
    }

}
