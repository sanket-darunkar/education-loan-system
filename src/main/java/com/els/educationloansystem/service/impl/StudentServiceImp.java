package com.els.educationloansystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.els.educationloansystem.dto.StudentDto;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.repository.StudentRepository;
import com.els.educationloansystem.service.StudentService;
import com.els.educationloansystem.util.StudentMapper;

@Service
public class StudentServiceImp implements StudentService{
	
	 @Autowired
	    private StudentRepository studentRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	   
	   @Autowired private StudentMapper studentMapper;


	   @Override
	   public void saveStudent(StudentDto studentDto) {

	       // 🔴 DUPLICATE EMAIL CHECK (MUST)
	       if (studentRepository.existsByEmail(studentDto.getEmail())) {
	           throw new RuntimeException("EMAIL_EXISTS");
	       }

	       // 🔐 ENCRYPT PASSWORD
	       studentDto.setPassword(
	           passwordEncoder.encode(studentDto.getPassword())
	       );

	       Student student = studentMapper.dtoToEntity(studentDto);
	       student.setRole("ROLE_STUDENT");
	       studentRepository.save(student);
	   }


}

