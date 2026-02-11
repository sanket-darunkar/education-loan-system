package com.els.educationloansystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDashboardSummaryDto {

    private long totalApplications;

    private long approvedApplications;
    private long rejectedApplications;
    private long pendingApplications;

    private long pendingDocuments;
    private long incorrectDocuments;
}
