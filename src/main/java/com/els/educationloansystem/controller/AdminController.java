package com.els.educationloansystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.els.educationloansystem.dto.AdminApplicationDetailsDto;
import com.els.educationloansystem.dto.AdminApplicationsTrendDto;
import com.els.educationloansystem.dto.AdminDashboardSummaryDto;
import com.els.educationloansystem.dto.AdminLoanApplicationDto;
import com.els.educationloansystem.jwt.JWTRequest;
import com.els.educationloansystem.jwt.JWTResponse;
import com.els.educationloansystem.jwt.JwtUtil;
import com.els.educationloansystem.repository.DocumentRepository;
import com.els.educationloansystem.repository.LoanApplicationRepository;
import com.els.educationloansystem.repository.StudentRepository;
import com.els.educationloansystem.service.AdminApplicationService;
import com.els.educationloansystem.service.AdminService;
import com.els.educationloansystem.service.LoanApplicationService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminApplicationService adminApplicationService;

    @Autowired
    private LoanApplicationService loanService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired LoanApplicationRepository loanApplicationRepository;

    @Autowired
    DocumentRepository documentRepository;
   
    @Autowired StudentRepository studentRepository;
    @PostMapping("/login")
    public JWTResponse adminLogin(@RequestBody JWTRequest request) {

        Authentication auth =
                adminService.authenticate(
                        request.getEmail(),
                        request.getPassword()
                );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtUtil.generateToken(
                request.getEmail(),
                "ADMIN"
        );

        return new JWTResponse(token);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/applications")
    public ResponseEntity<List<AdminLoanApplicationDto>> getAllApplications() {
        return ResponseEntity.ok(
                adminApplicationService.getAllApplications()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/applications/{applicationId}")
    public ResponseEntity<AdminApplicationDetailsDto> getApplicationDetails(
            @PathVariable Long applicationId
    ) {
        return ResponseEntity.ok(
                adminApplicationService.getApplicationDetails(applicationId)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/approve/{applicationId}")
    public ResponseEntity<String> approve(@PathVariable Long applicationId) {
        loanService.approveLoan(applicationId);
        return ResponseEntity.ok("Approved");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reject/{applicationId}")
    public ResponseEntity<String> reject(
            @PathVariable Long applicationId,
            @RequestParam String reason
    ) {
        loanService.rejectLoan(applicationId, reason);
        return ResponseEntity.ok("Rejected");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<?> adminMe(Authentication authentication) {

        return ResponseEntity.ok(
            Map.of(
                "email", authentication.getName(),
                "role", "ADMIN"
            )
        );
    }
    
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
