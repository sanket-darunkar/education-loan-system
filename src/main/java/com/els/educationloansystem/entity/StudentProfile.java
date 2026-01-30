package com.els.educationloansystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer age;
    private String phone;
    private String address;
    private String fatherName;
    private String motherName;
    private String gender;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private Student student;
}
