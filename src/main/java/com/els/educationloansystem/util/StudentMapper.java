package com.els.educationloansystem.util;

import org.springframework.stereotype.Component;

import com.els.educationloansystem.dto.StudentDto;
import com.els.educationloansystem.entity.Student;

@Component
public class StudentMapper {
	
	public Student dtoToEntity(StudentDto dto) {
		Student s = new Student();
		s.setName(dto.getName());
		s.setEmail(dto.getEmail());
		s.setPassword(dto.getPassword());
		s.setMobile(dto.getMobile());
		return s;
	}
	
	public StudentDto entityToDto(Student student) {
		StudentDto dto = new StudentDto();
		dto.setName(student.getName());
		dto.setEmail(student.getEmail());
		dto.setPassword(student.getPassword());
		dto.setMobile(student.getMobile());
		return dto;
	}

}
