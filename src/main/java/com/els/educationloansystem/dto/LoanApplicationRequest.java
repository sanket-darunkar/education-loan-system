package com.els.educationloansystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationRequest {

    private Double loanAmount;
    private String courseName;
    private String instituteName;
    private Integer courseDuration;
}
