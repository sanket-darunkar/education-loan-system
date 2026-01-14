package com.els.educationloansystem.dto;

import lombok.Data;

@Data
public class LoanApplicationRequest {

    private Long studentId;

    private Double loanAmount;
    private String courseName;
    private String instituteName;
    private Integer courseDuration;
}

