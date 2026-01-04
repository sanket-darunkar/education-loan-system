package com.els.educationloansystem.dto;

import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    private Long id;
    private Long studentId;
    private String documentType;
    private String status;
    private String remarks;
    private LocalDateTime uploadedAt;
    private MultipartFile file;

    
}
