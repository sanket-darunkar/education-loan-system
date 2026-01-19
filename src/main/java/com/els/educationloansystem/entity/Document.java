package com.els.educationloansystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long documentId;

	private String documentType;          // Aadhaar, PAN, Admission Letter
	private String documentUrl;           // file path or cloud url
	private LocalDate uploadedDate = LocalDate.now();

	private String verificationStatus;    // PENDING / VERIFIED / REJECTED

	@ManyToOne
	@JoinColumn(name = "application_id")
    @JsonBackReference   // ✅ STOPS LOOP
	private LoanApplication loanApplication;
}

