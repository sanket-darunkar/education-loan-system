package com.els.educationloansystem.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;

import com.els.educationloansystem.dto.LoanApplicationRequest;
import com.els.educationloansystem.dto.LoanApplicationResponse;
import com.els.educationloansystem.entity.LoanApplication;

public interface LoanApplicationService {

    LoanApplicationResponse applyLoan(LoanApplicationRequest request);

    void approveLoan(Long applicationId);

    void rejectLoan(Long applicationId, String reason);

	@Nullable
	Object getAllApplicationsForAdmin();
	
	Object getMyApplication(String email);

	@Nullable
	LoanApplication  getMyLatestApplication(Authentication authentication);

}

