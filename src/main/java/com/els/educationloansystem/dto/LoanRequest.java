package com.els.educationloansystem.dto;

import java.util.Objects;

import lombok.Data;

@Data
public class LoanRequest {
	
	 private int studentAge;
	    private double academicPercentage;
	    private boolean admissionConfirmed;
	    private boolean instituteApproved;
	    private double loanAmount;
	    private int cibilScore;
		public LoanRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
		public LoanRequest(int studentAge, double academicPercentage, boolean admissionConfirmed,
				boolean instituteApproved, double loanAmount, int cibilScore) {
			super();
			this.studentAge = studentAge;
			this.academicPercentage = academicPercentage;
			this.admissionConfirmed = admissionConfirmed;
			this.instituteApproved = instituteApproved;
			this.loanAmount = loanAmount;
			this.cibilScore = cibilScore;
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
		@Override
		public int hashCode() {
			return Objects.hash(academicPercentage, admissionConfirmed, cibilScore, instituteApproved, loanAmount,
					studentAge);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			LoanRequest other = (LoanRequest) obj;
			return Double.doubleToLongBits(academicPercentage) == Double.doubleToLongBits(other.academicPercentage)
					&& admissionConfirmed == other.admissionConfirmed && cibilScore == other.cibilScore
					&& instituteApproved == other.instituteApproved
					&& Double.doubleToLongBits(loanAmount) == Double.doubleToLongBits(other.loanAmount)
					&& studentAge == other.studentAge;
		}
	    
	    
	    

}
