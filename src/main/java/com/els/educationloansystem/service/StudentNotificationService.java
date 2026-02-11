package com.els.educationloansystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.els.educationloansystem.dto.StudentNotificationDto;
import com.els.educationloansystem.entity.Document;
import com.els.educationloansystem.entity.LoanApplication;

@Service
public class StudentNotificationService {

    public List<StudentNotificationDto> generateNotifications(
            List<LoanApplication> loans
    ) {
        List<StudentNotificationDto> notifications = new ArrayList<>();

        for (LoanApplication loan : loans) {

            // 🔔 Application status notifications
            if ("APPROVED".equals(loan.getApplicationStatus())) {
                notifications.add(new StudentNotificationDto(
                        "Your loan for " + loan.getCourseName() + " has been approved 🎉",
                        "SUCCESS",
                        loan.getApplicationDate()
                ));
            }

            if ("REJECTED".equals(loan.getApplicationStatus())) {
                notifications.add(new StudentNotificationDto(
                        "Your loan for " + loan.getCourseName() + " was rejected",
                        "ERROR",
                        loan.getApplicationDate()
                ));
            }

            if ("PENDING".equals(loan.getApplicationStatus())) {
                notifications.add(new StudentNotificationDto(
                        "Your loan application is under review",
                        "INFO",
                        loan.getApplicationDate()
                ));
            }

            // 📄 Document verification notifications
            if (loan.getDocuments() != null) {
                for (Document doc : loan.getDocuments()) {
                	if ("PENDING".equals(doc.getVerificationStatus())) {
                	    notifications.add(new StudentNotificationDto(
                	            "Document pending verification: " + doc.getDocumentType(),
                	            "WARNING",
                	            doc.getUploadedDate()
                	    ));
                	}
                }
            }
        }

        return notifications;
    }
}
