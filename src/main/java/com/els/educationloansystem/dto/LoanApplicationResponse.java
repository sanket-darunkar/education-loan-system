package com.els.educationloansystem.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationResponse {

    private Long applicationId;
    private Double loanAmount;
    private String courseName;
    private String instituteName;
    private Integer courseDuration;
    private String applicationStatus;
    private String eligibilityStatus;
    private LocalDate applicationDate;
}
