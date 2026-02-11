package com.els.educationloansystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.els.educationloansystem.dto.StudentProfileDto;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.repository.StudentProfileRepository;
import com.els.educationloansystem.repository.StudentRepository;
import com.els.educationloansystem.service.StudentProfileService;

@RestController
@RequestMapping("/api/student/profile")
@CrossOrigin("*")
public class StudentProfileController {

    @Autowired
    private StudentProfileService profileService;

    // 🔹 GET PROFILE (for auto-fill)
    @GetMapping
    public ResponseEntity<?> getProfile(Authentication authentication) {
        return ResponseEntity.ok(
                profileService.getProfile(authentication)
        );
    }

    // 🔹 SAVE / UPDATE PROFILE
    @PostMapping
    public ResponseEntity<?> saveProfile(
            @RequestBody StudentProfileDto dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                profileService.saveOrUpdate(dto)
        );
    }
}

