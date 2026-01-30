package com.els.educationloansystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.els.educationloansystem.dto.StudentDashboardSummaryDto;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.repository.StudentRepository;
import com.els.educationloansystem.service.StudentDashboardService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentDashboardService dashboardService;

    @GetMapping("/me")
    public Student getCurrentStudent(Authentication authentication) {
        String email = authentication.getName();
        return studentRepository.findByEmail(email).orElseThrow();
    }

    @GetMapping("/dashboard/summary")
    public StudentDashboardSummaryDto getDashboardSummary(Authentication authentication) {

        String email = authentication.getName();

        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow();

        return dashboardService.getDashboardSummary(student.getId());
    }
}

