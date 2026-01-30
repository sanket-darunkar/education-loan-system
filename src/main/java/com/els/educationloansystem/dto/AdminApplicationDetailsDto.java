package com.els.educationloansystem.dto;

import java.time.LocalDate;
import java.util.List;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminApplicationDetailsDto {

	 // APPLICATION
    private Long applicationId;
    private Double loanAmount;
    private String courseName;
    private String instituteName;
    private Integer courseDuration;
    private String applicationStatus;
    private String eligibilityStatus;
    private LocalDate applicationDate;

    // STUDENT
    private Long studentId;
    private String studentName;
    private String studentEmail;

    // DOCUMENTS
    private List<AdminDocumentViewDto> documents;
    }

