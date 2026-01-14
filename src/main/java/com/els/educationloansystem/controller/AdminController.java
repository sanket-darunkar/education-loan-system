package com.els.educationloansystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.els.educationloansystem.service.LoanApplicationService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/approve/{applicationId}")
    public ResponseEntity<String> approveLoan(@PathVariable Long applicationId) {

        loanApplicationService.approveLoan(applicationId);
        return ResponseEntity.ok("Loan Approved Successfully");
    }

    @PostMapping("/reject/{applicationId}")
    public ResponseEntity<String> rejectLoan(
            @PathVariable Long applicationId,
            @RequestParam String reason) {

        loanApplicationService.rejectLoan(applicationId, reason);
        return ResponseEntity.ok("Loan Rejected");
    }
}
