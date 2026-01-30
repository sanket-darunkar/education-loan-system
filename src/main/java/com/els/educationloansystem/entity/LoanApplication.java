package com.els.educationloansystem.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    private Double loanAmount;
    private String courseName;
    private String instituteName;
    private Integer courseDuration;

    private String eligibilityStatus;   // PENDING / ELIGIBLE / NOT_ELIGIBLE
    private String applicationStatus;   // PENDING / APPROVED / REJECTED

    private LocalDate applicationDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @OneToMany(
        mappedBy = "loanApplication",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Document> documents;
}

