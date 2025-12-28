package com.els.educationloansystem.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.els.educationloansystem.dto.LoanRequest;
import com.els.educationloansystem.entity.LoanEligibility;
import com.els.educationloansystem.repository.LoanRepository;

public class LoanServiceImpl  implements LoanService{
	@Autowired
    private LoanRepository loanRepository;

    @Override
    public String checkEligibility(LoanRequest request) {

        if (request.getStudentAge() < 18 || request.getStudentAge() > 35)
            return "NOT ELIGIBLE: Age criteria failed";

        if (!request.isAdmissionConfirmed())
            return "NOT ELIGIBLE: Admission not confirmed";

        if (request.getAcademicPercentage() < 50)
            return "NOT ELIGIBLE: Academic percentage too low";

        if (!request.isInstituteApproved())
            return "NOT ELIGIBLE: Institute not approved";

        if (request.getCibilScore() < 650)
            return "NOT ELIGIBLE: Low CIBIL score";

        LoanEligibility entity = new LoanEligibility();
        entity.setStudentAge(request.getStudentAge());
        entity.setAcademicPercentage(request.getAcademicPercentage());
        entity.setAdmissionConfirmed(request.isAdmissionConfirmed());
        entity.setInstituteApproved(request.isInstituteApproved());
        entity.setLoanAmount(request.getLoanAmount());
        entity.setCibilScore(request.getCibilScore());
        entity.setEligibilityStatus("ELIGIBLE");

        loanRepository.save(entity);

        return "ELIGIBLE";
    }

}
