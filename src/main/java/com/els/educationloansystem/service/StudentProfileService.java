package com.els.educationloansystem.service;


import org.springframework.security.core.Authentication;

import com.els.educationloansystem.dto.StudentProfileDto;
import com.els.educationloansystem.entity.StudentProfile;

public interface StudentProfileService {
    StudentProfile saveOrUpdate(StudentProfileDto dto);

	
	StudentProfile getProfile(Authentication authentication);
}
