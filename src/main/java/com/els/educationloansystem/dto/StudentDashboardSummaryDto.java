package com.els.educationloansystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDashboardSummaryDto {
    private long totalLoans;
    private long approvedLoans;
    private double outstandingBalance;
    private double upcomingEmi;
    private int creditScore;
}
