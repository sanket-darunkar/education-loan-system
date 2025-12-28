package com.els.educationloansystem.service;

import com.els.educationloansystem.dto.LoanRequest;

public interface LoanService {
	String checkEligibility(LoanRequest request);

}
