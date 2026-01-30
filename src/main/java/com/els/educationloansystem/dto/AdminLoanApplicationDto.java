package com.els.educationloansystem.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdminLoanApplicationDto {
	private Long applicationId;
    private Double loanAmount;
    private String courseName;
    private String instituteName;
    private Integer courseDuration;

    private String applicationStatus;
    private String eligibilityStatus;
    private LocalDate applicationDate;

    // 👇 STUDENT DETAILS (ADMIN ONLY)
    private Long studentId;
    private String studentName;
    private String studentEmail;
}
