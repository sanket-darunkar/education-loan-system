package com.els.educationloansystem.service;

import com.els.educationloansystem.dto.LoanApplicationRequest;
import com.els.educationloansystem.entity.LoanApplication;

public interface LoanApplicationService {

    LoanApplication applyLoan(LoanApplicationRequest request);

    void approveLoan(Long applicationId);

    void rejectLoan(Long applicationId, String reason);
}

