package com.els.educationloansystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.els.educationloansystem.dto.LoanRequest;
import com.els.educationloansystem.service.LoanService;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

	@Autowired
    private LoanService loanService;

    @PostMapping("/check-eligibility")
    public ResponseEntity<String> checkEligibility(@RequestBody LoanRequest request) {
        String result = loanService.checkEligibility(request);
        return ResponseEntity.ok(result);
    }
}
