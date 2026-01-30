package com.els.educationloansystem.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.els.educationloansystem.dto.AdminApplicationDetailsDto;
import com.els.educationloansystem.dto.AdminLoanApplicationDto;

public interface AdminApplicationService {

    List<AdminLoanApplicationDto> getAllApplications();

    AdminApplicationDetailsDto getApplicationDetails(Long applicationId);
}