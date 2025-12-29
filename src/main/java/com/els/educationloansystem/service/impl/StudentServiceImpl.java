package com.els.educationloansystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.els.educationloansystem.dto.StudentLoginDTO;
import com.els.educationloansystem.dto.StudentRegisterDTO;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.repository.StudentRepository;
import com.els.educationloansystem.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public Student registerStudent(StudentRegisterDTO registerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student loginStudent(StudentLoginDTO loginDTO) {
		// TODO Auto-generated method stub
		return null;
	}

//    @Override
//    public Student registerStudent(Student student) {
//        student.setPassword(encoder.encode(student.getPassword()));
//        return studentRepository.save(student);
//    }
//
//    @Override
//    public Student loginStudent(String email, String password) {
//        Student student = studentRepository.findByEmail(email);
//        if (student != null && encoder.matches(password, student.getPassword())) {
//            return student;
//        }
//        return null;
//    }
}
