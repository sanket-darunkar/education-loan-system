package com.els.educationloansystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.els.educationloansystem.dto.AdminApplicationsTrendDto;
import com.els.educationloansystem.dto.AdminDashboardSummaryDto;
import com.els.educationloansystem.jwt.JWTRequest;
import com.els.educationloansystem.jwt.JWTResponse;
import com.els.educationloansystem.jwt.JwtUtil;
import com.els.educationloansystem.repository.DocumentRepository;
import com.els.educationloansystem.repository.LoanApplicationRepository;
import com.els.educationloansystem.repository.StudentRepository;
import com.els.educationloansystem.service.LoanApplicationService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired StudentRepository studentRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // ===== ADMIN LOGIN =====
    @PostMapping("/login")
    public JWTResponse adminLogin(@RequestBody JWTRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(
                    request.getEmail(),
                    "ADMIN"
            );
            return new JWTResponse(token);
        }

        throw new RuntimeException("Invalid admin credentials");
    }

    // ===== ALL APPLICATIONS =====
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/applications")
    public ResponseEntity<?> getAllApplications() {
        return ResponseEntity.ok(
                loanApplicationService.getAllApplicationsForAdmin()
        );
    }

    // ===== APPROVE =====
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/approve/{applicationId}")
    public ResponseEntity<String> approveLoan(
            @PathVariable Long applicationId) {

        loanApplicationService.approveLoan(applicationId);
        return ResponseEntity.ok("Loan Approved Successfully");
    }

    // ===== REJECT =====
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reject/{applicationId}")
    public ResponseEntity<String> rejectLoan(
            @PathVariable Long applicationId,
            @RequestParam String reason) {

        loanApplicationService.rejectLoan(applicationId, reason);
        return ResponseEntity.ok("Loan Rejected");
    }

    // ===== ADMIN PROFILE =====
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<?> getAdminProfile(Authentication auth) {

        return ResponseEntity.ok(
                Map.of(
                        "name", auth.getName(),
                        "role", "ADMIN"
                )
        );
    }

    // ===== DASHBOARD SUMMARY =====
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard/summary")
    public ResponseEntity<AdminDashboardSummaryDto> getDashboardSummary() {

        long total = loanApplicationRepository.count();
        long approved =
                loanApplicationRepository.countByApplicationStatus("APPROVED");
        long rejected =
                loanApplicationRepository.countByApplicationStatus("REJECTED");
        long pending =
                loanApplicationRepository.countByApplicationStatus("PENDING");

        long pendingDocs =
                documentRepository.countByVerificationStatus("PENDING");
        long incorrectDocs =
                documentRepository.countByVerificationStatus("REJECTED");

        return ResponseEntity.ok(
                new AdminDashboardSummaryDto(
                        total,
                        approved,
                        rejected,
                        pending,
                        pendingDocs,
                        incorrectDocs
                )
        );
    }

    // ===== DASHBOARD TREND =====
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard/trend")
    public ResponseEntity<List<AdminApplicationsTrendDto>>
    getApplicationsTrend() {

        List<Object[]> rawData =
                loanApplicationRepository.countApplicationsGroupedByDate();

        List<AdminApplicationsTrendDto> trend =
                rawData.stream()
                        .map(row -> new AdminApplicationsTrendDto(
                                row[0].toString(),
                                (Long) row[1]
                        ))
                        .toList();

        return ResponseEntity.ok(trend);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approvals")
    public ResponseEntity<?> getApplicationsForApproval() {

        // Only PENDING applications
        return ResponseEntity.ok(
            loanApplicationRepository.findByApplicationStatus("PENDING")
        );
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }


}
