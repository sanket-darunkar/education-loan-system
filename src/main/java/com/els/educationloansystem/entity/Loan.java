package com.els.educationloansystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private Double interestRate;
    private Integer tenure;
    private LocalDate approvedDate;
    private String loanStatus; // ACTIVE / CLOSED

    @OneToOne
    private LoanApplication application;
}
