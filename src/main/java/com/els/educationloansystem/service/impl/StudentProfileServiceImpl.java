package com.els.educationloansystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.els.educationloansystem.dto.StudentProfileDto;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.entity.StudentProfile;
import com.els.educationloansystem.repository.StudentProfileRepository;
import com.els.educationloansystem.repository.StudentRepository;
import com.els.educationloansystem.service.StudentProfileService;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentProfileRepository profileRepository;

    public StudentProfile saveOrUpdate(StudentProfileDto dto) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow();

        StudentProfile profile = profileRepository
                .findByStudent_Id(student.getId())
                .orElse(new StudentProfile());

        profile.setStudent(student);
        profile.setAge(dto.getAge());
        profile.setPhone(dto.getPhone());
        profile.setAddress(dto.getAddress());
        profile.setFatherName(dto.getFatherName());
        profile.setMotherName(dto.getMotherName());
        profile.setGender(dto.getGender());

        return profileRepository.save(profile);
    }
}
