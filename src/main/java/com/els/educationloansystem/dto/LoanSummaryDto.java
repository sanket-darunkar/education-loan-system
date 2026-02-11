package com.els.educationloansystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanSummaryDto {

    private Long applicationId;
    private String courseName;
    private Double loanAmount;
    private String applicationStatus;
}