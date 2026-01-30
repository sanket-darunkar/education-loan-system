package com.els.educationloansystem.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.els.educationloansystem.dto.AdminDashboardSummaryDto;
import com.els.educationloansystem.jwt.JWTRequest;
import com.els.educationloansystem.jwt.JWTResponse;
import com.els.educationloansystem.jwt.JwtUtil;
import com.els.educationloansystem.repository.DocumentRepository;
import com.els.educationloansystem.repository.LoanApplicationRepository;
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

    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public JWTResponse adminLogin(@RequestBody JWTRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(
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
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/applications")
    public ResponseEntity<?> getAllApplications() {
        return ResponseEntity.ok(
            this.loanApplicationService.getAllApplicationsForAdmin()
        );
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/approve/{applicationId}")
    public ResponseEntity<String> approveLoan(@PathVariable Long applicationId) {

        loanApplicationService.approveLoan(applicationId);
        return ResponseEntity.ok("Loan Approved Successfully");
    }
    
    

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reject/{applicationId}")
    public ResponseEntity<String> rejectLoan(
            @PathVariable Long applicationId,
            @RequestParam String reason) {

        loanApplicationService.rejectLoan(applicationId, reason);
        return ResponseEntity.ok("Loan Rejected");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<?> getAdminProfile(Authentication authentication) {

        return ResponseEntity.ok(
            Map.of(
                "name", authentication.getName(),
                "role", "ADMIN"
            )
        );
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard/summary")
    public ResponseEntity<AdminDashboardSummaryDto> getDashboardSummary() {

        long totalApplications = loanApplicationRepository.count();
        long approvedLoans = loanApplicationRepository.countByApplicationStatus("APPROVED");

        long pendingDocuments = documentRepository.countByVerificationStatus("PENDING");
        long incorrectDocuments = documentRepository.countByVerificationStatus("REJECTED");

        AdminDashboardSummaryDto summary =
            new AdminDashboardSummaryDto(
                totalApplications,
                pendingDocuments,
                incorrectDocuments,
                approvedLoans
            );

        return ResponseEntity.ok(summary);
    }

}
