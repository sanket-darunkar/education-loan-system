package com.els.educationloansystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.repository.LoanApplicationRepository;
import com.els.educationloansystem.repository.StudentRepository;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("*")
public class StudentLoanController {

    @Autowired
    private LoanApplicationRepository loanRepo;

    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/loans")
    public List<LoanApplication> getMyLoans(Authentication authentication) {

        String email = authentication.getName();

        Student student = studentRepo
                .findByEmail(email)
                .orElseThrow();

        return loanRepo.findByStudent_Id(student.getId());
    }
}
