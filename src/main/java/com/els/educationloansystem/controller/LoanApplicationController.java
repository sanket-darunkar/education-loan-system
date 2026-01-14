package com.els.educationloansystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.els.educationloansystem.dto.LoanApplicationRequest;
import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.service.LoanApplicationService;

@RestController
@RequestMapping("/api/loan-application")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/apply")
    public ResponseEntity<LoanApplication> applyLoan(
            @RequestBody LoanApplicationRequest request) {

        LoanApplication application =
                loanApplicationService.applyLoan(request);

        return ResponseEntity.ok(application);
    }
}
