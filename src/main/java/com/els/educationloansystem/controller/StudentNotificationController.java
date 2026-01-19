package com.els.educationloansystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.els.educationloansystem.dto.StudentNotificationDto;
import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.repository.LoanApplicationRepository;
import com.els.educationloansystem.repository.StudentRepository;
import com.els.educationloansystem.service.StudentNotificationService;

@RestController
@RequestMapping("/api/student/dashboard")
@CrossOrigin("*")
public class StudentNotificationController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private StudentNotificationService notificationService;

    @GetMapping("/notifications")
    public List<StudentNotificationDto> getNotifications(
            Authentication authentication
    ) {
        String email = authentication.getName();

        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow();

        // ✅ FETCH LOANS EXPLICITLY
        List<LoanApplication> loans =
                loanApplicationRepository.findByStudentId(student.getId());

        return notificationService.generateNotifications(loans);
    }
}
