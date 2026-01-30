package com.els.educationloansystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    private String documentType;
    private String documentUrl;
    private LocalDate uploadedDate = LocalDate.now();
    private String verificationStatus;

    @ManyToOne
    @JoinColumn(name = "application_id")
    @JsonIgnore
    private LoanApplication loanApplication;
}

