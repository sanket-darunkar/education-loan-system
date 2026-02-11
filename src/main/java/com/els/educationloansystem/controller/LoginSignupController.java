
package com.els.educationloansystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.els.educationloansystem.dto.LoanSummaryDto;
import com.els.educationloansystem.dto.StudentDashboardSummaryDto;
import com.els.educationloansystem.dto.StudentDto;
import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.jwt.JWTRequest;
import com.els.educationloansystem.jwt.JWTResponse;
import com.els.educationloansystem.jwt.JwtUtil;
import com.els.educationloansystem.repository.LoanApplicationRepository;
import com.els.educationloansystem.repository.StudentRepository;
import com.els.educationloansystem.service.StudentDashboardService;
import com.els.educationloansystem.service.StudentService;

@RestController
@CrossOrigin("*")
//@CrossOrigin("*")
@RequestMapping("/api/auth")
public class LoginSignupController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired StudentService service;
	
	@Autowired StudentRepository studentRepository;
	
	@Autowired
    private LoanApplicationRepository loanRepo;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> saveStudent(@RequestBody StudentDto studentDto) {
	    try {
	        service.saveStudent(studentDto);
	        return ResponseEntity.ok("Student Registered Successfully");
	    } catch (RuntimeException e) {
	        if ("EMAIL_EXISTS".equals(e.getMessage())) {
	            return ResponseEntity
	                    .badRequest()
	                    .body("Email already exists");
	        }
	        return ResponseEntity
	                .internalServerError()
	                .body("Registration failed");
	    }
	}
	
	
	@PostMapping("/login")
	public JWTResponse login(@RequestBody JWTRequest request) {

		
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
	                "STUDENT"
	        );
	        return new JWTResponse(token);
	    }

		throw new RuntimeException("Invalid username or password");
	   
	}

	@GetMapping("/me")
	public Student getCurrentStudent(Authentication authentication) {
		String email = authentication.getName();
		return studentRepository.findByEmail(email).orElseThrow();
	   
	}
	
	@Autowired
	private StudentDashboardService dashboardService;

	@GetMapping("/dashboard/summary")
	public StudentDashboardSummaryDto getDashboardSummary(Authentication authentication) {

	    String email = authentication.getName();

	    Student student = studentRepository
	            .findByEmail(email)
	            .orElseThrow();

	    return dashboardService.getDashboardSummary(student.getId());
	}


	



}