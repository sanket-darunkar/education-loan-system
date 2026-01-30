package com.els.educationloansystem.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.els.educationloansystem.entity.Document;
import com.els.educationloansystem.repository.DocumentRepository;

@RestController
@RequestMapping("/api/admin/viewdocuments")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping("/view/{documentId}")
    public ResponseEntity<Resource> viewDocument(@PathVariable Long documentId) throws IOException {

        Document doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        Path filePath = Paths.get(doc.getDocumentUrl());
        Resource resource = new FileSystemResource(filePath);

        String contentType = Files.probeContentType(filePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + filePath.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @PostMapping("/{documentId}/verify")
    public ResponseEntity<String> verifyDocument(@PathVariable Long documentId) {
        Document doc = documentRepository.findById(documentId).orElseThrow();
        doc.setVerificationStatus("VERIFIED");
        documentRepository.save(doc);
        return ResponseEntity.ok("Verified");
    }

    @PostMapping("/{documentId}/reject")
    public ResponseEntity<String> rejectDocument(@PathVariable Long documentId) {
        Document doc = documentRepository.findById(documentId).orElseThrow();
        doc.setVerificationStatus("REJECTED");
        documentRepository.save(doc);
        return ResponseEntity.ok("Rejected");
    }
}
