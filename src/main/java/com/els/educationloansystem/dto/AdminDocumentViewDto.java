package com.els.educationloansystem.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDocumentViewDto {

    
	private Long documentId;
    private String documentType;
    private String documentUrl;
    private String verificationStatus;
    private LocalDate uploadedDate;
}
