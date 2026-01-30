package com.els.educationloansystem.service;

import com.els.educationloansystem.dto.StudentProfileDto;
import com.els.educationloansystem.entity.StudentProfile;

public interface StudentProfileService {
    StudentProfile saveOrUpdate(StudentProfileDto dto);
}
