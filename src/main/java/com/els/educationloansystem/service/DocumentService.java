package com.els.educationloansystem.service;

import java.util.List;



import org.springframework.web.multipart.MultipartFile;

import com.els.educationloansystem.dto.DocumentDto;
import com.els.educationloansystem.entity.Document;



public interface DocumentService {

	 void uploadDocument(DocumentDto dto, MultipartFile file);

	    List<Document> getDocumentsByStudent(Long studentId);

	    void updateDocumentStatus(Long docId, String status, String remarks);

		
}
