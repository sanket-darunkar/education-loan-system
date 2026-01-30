package com.els.educationloansystem.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.els.educationloansystem.entity.Document;
import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.repository.DocumentRepository;
import com.els.educationloansystem.repository.LoanApplicationRepository;

@RestController
@RequestMapping("/api/student/documents")
@CrossOrigin("*")
public class DocumentController {

    private static final String UPLOAD_DIR = "uploads/loan-documents/";

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @PostMapping("/upload/{applicationId}")
    public ResponseEntity<?> uploadDocument(
            @PathVariable Long applicationId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("documentType") String documentType
    ) {
        try {
            LoanApplication application = loanApplicationRepository
                    .findById(applicationId)
                    .orElseThrow(() -> new RuntimeException("Application not found"));

            // 📁 Create folder per application
            String folderPath = UPLOAD_DIR + "application_" + applicationId;
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // 📄 Save file
            Path filePath = Paths.get(folderPath, file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            // 💾 Save document entry
            Document doc = new Document();
            doc.setDocumentType(documentType);
            doc.setDocumentUrl(filePath.toString());
            doc.setVerificationStatus("PENDING");
            doc.setLoanApplication(application);

            documentRepository.save(doc);

            return ResponseEntity.ok("Document uploaded successfully");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 👀 View documents of application
    @GetMapping("/{applicationId}")
    public ResponseEntity<?> getDocuments(@PathVariable Long applicationId) {
        return ResponseEntity.ok(
                documentRepository.findByLoanApplication_ApplicationId(applicationId)
        );
    }
}
