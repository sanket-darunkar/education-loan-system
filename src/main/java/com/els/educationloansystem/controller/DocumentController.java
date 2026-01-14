package com.els.educationloansystem.controller;

import com.els.educationloansystem.dto.DocumentDto;
import com.els.educationloansystem.entity.Document;
import com.els.educationloansystem.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/student/documents")
@CrossOrigin
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	// Student uploads document
	@PostMapping("/upload")
	public ResponseEntity<Document> uploadDocument(
			@RequestParam Long applicationId,
			@RequestParam String documentType,
			@RequestParam MultipartFile file
	) {
		DocumentDto dto = new DocumentDto();
		dto.setApplicationId(applicationId);
		dto.setDocumentType(documentType);
		dto.setFile(file);

		return ResponseEntity.ok(documentService.uploadDocument(dto));
	}

	// Get all documents of application
	@GetMapping("/application/{applicationId}")
	public ResponseEntity<List<Document>> getDocuments(@PathVariable Long applicationId) {
		return ResponseEntity.ok(
				documentService.getDocumentsByApplication(applicationId)
		);
	}

	// Admin verifies document
	@PostMapping("/verify/{documentId}")
	public ResponseEntity<String> verifyDocument(
			@PathVariable Long documentId,
			@RequestParam String status
	) {
		documentService.verifyDocument(documentId, status);
		return ResponseEntity.ok("Document status updated");
	}
}
