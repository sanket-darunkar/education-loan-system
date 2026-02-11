package com.els.educationloansystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.els.educationloansystem.dto.LoanApplicationRequest;
import com.els.educationloansystem.dto.LoanApplicationResponse;
import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.service.LoanApplicationService;

@RestController
@RequestMapping("/api/loan-application")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/apply")
    public ResponseEntity<LoanApplicationResponse> applyLoan(
            @RequestBody LoanApplicationRequest request) {

        return ResponseEntity.ok(
            loanApplicationService.applyLoan(request)
        );
    }
    
    @GetMapping("/my")
    public ResponseEntity<?> getMyApplication() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return ResponseEntity.ok(
                loanApplicationService.getMyApplication(email)
        );
    }
    
    @GetMapping("/my-application")
    public ResponseEntity<?> getMyApplication(Authentication authentication) {
        return ResponseEntity.ok(
            loanApplicationService.getMyLatestApplication(authentication)
        );
    }

    
    
    
    

}
