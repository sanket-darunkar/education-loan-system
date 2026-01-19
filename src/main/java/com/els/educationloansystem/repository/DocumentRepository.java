package com.els.educationloansystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.els.educationloansystem.entity.Document;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findByLoanApplication_ApplicationId(Long applicationId);
	
	long countByVerificationStatus(String verificationStatus);
}
