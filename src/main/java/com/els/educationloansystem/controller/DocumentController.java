package com.els.educationloansystem.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.els.educationloansystem.entity.Document;
import com.els.educationloansystem.entity.LoanApplication;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.repository.DocumentRepository;
import com.els.educationloansystem.repository.LoanApplicationRepository;
import com.els.educationloansystem.repository.StudentRepository;

@RestController
@RequestMapping("/api/student/documents")
@CrossOrigin("*")
public class DocumentController {

    private static final String UPLOAD_DIR = "uploads/loan-documents/";

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/upload/{applicationId}")
    public ResponseEntity<?> uploadDocument(
            @PathVariable Long applicationId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("documentType") String documentType,
            Authentication authentication
    ) {
        try {
            // 🔐 logged-in student
            String email = authentication.getName();
            Student student = studentRepository.findByEmail(email).orElseThrow();

            LoanApplication application = loanApplicationRepository
                    .findById(applicationId)
                    .orElseThrow(() -> new RuntimeException("Application not found"));

            // 🔒 SECURITY CHECK
            if (!application.getStudent().getId().equals(student.getId())) {
                return ResponseEntity.status(403).body("Unauthorized upload");
            }

            if (!"PENDING".equals(application.getApplicationStatus())) {
                return ResponseEntity.badRequest()
                        .body("Documents allowed only for pending applications");
            }

            // 📁 Create folder
            String folderPath = UPLOAD_DIR + "application_" + applicationId;
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            // 📄 Save file
            Path filePath = Paths.get(folderPath, file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            // 💾 Save document
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

    // 👀 Student views own documents
    @GetMapping("/{applicationId}")
    public ResponseEntity<?> getDocuments(
            @PathVariable Long applicationId,
            Authentication authentication
    ) {
        String email = authentication.getName();
        Student student = studentRepository.findByEmail(email).orElseThrow();

        LoanApplication app = loanApplicationRepository
                .findById(applicationId)
                .orElseThrow();

        if (!app.getStudent().getId().equals(student.getId())) {
            return ResponseEntity.status(403).body("Unauthorized");
        }

        return ResponseEntity.ok(
                documentRepository.findByLoanApplication_ApplicationId(applicationId)
        );
    }
}
