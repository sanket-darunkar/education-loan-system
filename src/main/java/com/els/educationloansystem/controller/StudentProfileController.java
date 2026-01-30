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

    @Autowired
    private StudentProfileRepository profileRepository;

    @Autowired
    private StudentRepository studentRepository;

    // GET PROFILE (AUTO-FILL FORM)
    @GetMapping
    public ResponseEntity<?> getProfile(Authentication auth) {

        Student student = studentRepository
                .findByEmail(auth.getName())
                .orElseThrow();

        return ResponseEntity.ok(
            profileRepository.findByStudent_Id(student.getId()).orElse(null)
        );
    }

    // SAVE / UPDATE PROFILE
    @PostMapping
    public ResponseEntity<?> saveProfile(
            @RequestBody StudentProfileDto dto) {

        return ResponseEntity.ok(
            profileService.saveOrUpdate(dto)
        );
    }
}

