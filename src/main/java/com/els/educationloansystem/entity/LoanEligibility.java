package com.els.educationloansystem.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LoanEligibility {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int studentAge;
    private double academicPercentage;
    private boolean admissionConfirmed;
    private boolean instituteApproved;
    private double loanAmount;
    private int cibilScore;
    private String eligibilityStatus;
	public LoanEligibility() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoanEligibility(Long id, int studentAge, double academicPercentage, boolean admissionConfirmed,
			boolean instituteApproved, double loanAmount, int cibilScore, String eligibilityStatus) {
		super();
		this.id = id;
		this.studentAge = studentAge;
		this.academicPercentage = academicPercentage;
		this.admissionConfirmed = admissionConfirmed;
		this.instituteApproved = instituteApproved;
		this.loanAmount = loanAmount;
		this.cibilScore = cibilScore;
		this.eligibilityStatus = eligibilityStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	public double getAcademicPercentage() {
		return academicPercentage;
	}
	public void setAcademicPercentage(double academicPercentage) {
		this.academicPercentage = academicPercentage;
	}
	public boolean isAdmissionConfirmed() {
		return admissionConfirmed;
	}
	public void setAdmissionConfirmed(boolean admissionConfirmed) {
		this.admissionConfirmed = admissionConfirmed;
	}
	public boolean isInstituteApproved() {
		return instituteApproved;
	}
	public void setInstituteApproved(boolean instituteApproved) {
		this.instituteApproved = instituteApproved;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getCibilScore() {
		return cibilScore;
	}
	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}
	public String getEligibilityStatus() {
		return eligibilityStatus;
	}
	public void setEligibilityStatus(String eligibilityStatus) {
		this.eligibilityStatus = eligibilityStatus;
	}
	@Override
	public int hashCode() {
		return Objects.hash(academicPercentage, admissionConfirmed, cibilScore, eligibilityStatus, id,
				instituteApproved, loanAmount, studentAge);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanEligibility other = (LoanEligibility) obj;
		return Double.doubleToLongBits(academicPercentage) == Double.doubleToLongBits(other.academicPercentage)
				&& admissionConfirmed == other.admissionConfirmed && cibilScore == other.cibilScore
				&& Objects.equals(eligibilityStatus, other.eligibilityStatus) && Objects.equals(id, other.id)
				&& instituteApproved == other.instituteApproved
				&& Double.doubleToLongBits(loanAmount) == Double.doubleToLongBits(other.loanAmount)
				&& studentAge == other.studentAge;
	}

    
}
