package com.els.educationloansystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.els.educationloansystem.dto.AdminApplicationDetailsDto;
import com.els.educationloansystem.dto.AdminDocumentViewDto;
import com.els.educationloansystem.dto.AdminLoanApplicationDto;
import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.repository.DocumentRepository;
import com.els.educationloansystem.repository.LoanApplicationRepository;
import com.els.educationloansystem.service.AdminApplicationService;

@Service
public class AdminApplicationServiceImpl implements AdminApplicationService {

    @Autowired
    private LoanApplicationRepository loanRepo;

    @Autowired
    private DocumentRepository documentRepo;

    @Override
    public List<AdminLoanApplicationDto> getAllApplications() {

        return loanRepo.findAll()
                .stream()
                .map(app -> new AdminLoanApplicationDto(
                        app.getApplicationId(),
                        app.getLoanAmount(),
                        app.getCourseName(),
                        app.getInstituteName(),
                        app.getCourseDuration(),
                        app.getApplicationStatus(),
                        app.getEligibilityStatus(),
                        app.getApplicationDate(),
                        app.getStudent().getId(),
                        app.getStudent().getName(),
                        app.getStudent().getEmail()
                ))
                .toList();
    }

    @Override
    public AdminApplicationDetailsDto getApplicationDetails(Long applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        AdminApplicationDetailsDto dto = new AdminApplicationDetailsDto();

        dto.setApplicationId(app.getApplicationId());
        dto.setLoanAmount(app.getLoanAmount());
        dto.setCourseName(app.getCourseName());
        dto.setInstituteName(app.getInstituteName());
        dto.setCourseDuration(app.getCourseDuration());
        dto.setApplicationStatus(app.getApplicationStatus());
        dto.setEligibilityStatus(app.getEligibilityStatus());
        dto.setApplicationDate(app.getApplicationDate());

        dto.setStudentId(app.getStudent().getId());
        dto.setStudentName(app.getStudent().getName());
        dto.setStudentEmail(app.getStudent().getEmail());

        dto.setDocuments(
            documentRepo.findByLoanApplication_ApplicationId(applicationId)
                .stream()
                .map(d -> new AdminDocumentViewDto(
                        d.getDocumentId(),
                        d.getDocumentType(),
                        d.getDocumentUrl(),
                        d.getVerificationStatus(),
                        d.getUploadedDate()
                ))
                .toList()
        );

        return dto;
    }
}

