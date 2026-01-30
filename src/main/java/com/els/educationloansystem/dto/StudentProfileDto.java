package com.els.educationloansystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileDto {

    private Integer age;
    private String phone;
    private String address;
    private String fatherName;
    private String motherName;
    private String gender;
}
