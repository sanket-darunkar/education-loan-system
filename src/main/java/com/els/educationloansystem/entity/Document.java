package com.els.educationloansystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private Long studentId;
	private String documentType;
	private String fileName;
	private String filePath;
	private String status;
	private String remarks;
	private LocalDateTime uploadedAt;
	

}
