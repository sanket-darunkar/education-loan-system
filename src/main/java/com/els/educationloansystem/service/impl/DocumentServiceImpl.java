package com.els.educationloansystem.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.els.educationloansystem.dto.DocumentDto;
import com.els.educationloansystem.entity.Document;
import com.els.educationloansystem.repository.DocumentRepository;
import com.els.educationloansystem.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired private DocumentRepository documentRepository;
	
	private final String UPLOAD_DIR = "uploads/";

	@Override
	public void uploadDocument(DocumentDto dto, MultipartFile file) {
		
		String fileName = file.getOriginalFilename();
	    String path = "uploads/" + fileName;

	    try {
	        Files.copy(file.getInputStream(), Paths.get(path));
	    } catch (IOException e) {
	        throw new RuntimeException("File upload failed");
	    }

	    Document document = new Document();
	    document.setStudentId(dto.getStudentId());
	    document.setDocumentType(dto.getDocumentType());
	    document.setFileName(fileName);
	    document.setFilePath(path);
	    document.setStatus("PENDING");
	    document.setUploadedAt(LocalDateTime.now());

	    documentRepository.save(document);
		
	}

	@Override
	public List<Document> getDocumentsByStudent(Long studentId) {
		
		return this.documentRepository.findByStudentId(studentId);
	}

	@Override
	public void updateDocumentStatus(Long docId, String status, String remarks) {
		 Document doc = this.documentRepository.findById(docId);
	        doc.setStatus(status);
	        doc.setRemarks(remarks);
	        documentRepository.save(doc);
		
	}
	
	
}
