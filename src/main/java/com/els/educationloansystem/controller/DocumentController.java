package com.els.educationloansystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.els.educationloansystem.dto.DocumentDto;
import com.els.educationloansystem.dto.StudentDto;
import com.els.educationloansystem.entity.Document;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.service.DocumentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/documents")
public class DocumentController {
    
	@Autowired
	private DocumentService documentService;
	
	@GetMapping("/doc")
	public String toUploadDocumetnt() {
		return "UploadDocuments";
	}

	@PostMapping("/upload")
	public String uploadDocument(@RequestParam("file") MultipartFile file,
			@RequestParam("documentType") String documentType, HttpSession session) {

		Student student = (Student) session.getAttribute("loggedInStudent");

		DocumentDto dto = new DocumentDto();
		dto.setStudentId(student.getId());
		dto.setDocumentType(documentType);

		documentService.uploadDocument(dto, file);

		return "redirect:/documents/status";
	}

	@GetMapping("/status")
	public String documentStatus(HttpSession session, Model model) {
		Student student = (Student) session.getAttribute("loggedInStudent");
		model.addAttribute("documents", documentService.getDocumentsByStudent(student.getId()));
		return "DocumentStatus";
	}
}
