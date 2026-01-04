package com.els.educationloansystem.util;

import com.els.educationloansystem.dto.DocumentDto;
import com.els.educationloansystem.entity.Document;

public class DocumentMapper {

    public static Document dtoToEntity(DocumentDto dto) {

        Document doc = new Document();
        doc.setId(dto.getId());
        doc.setStudentId(dto.getStudentId());
        doc.setDocumentType(dto.getDocumentType());
        doc.setStatus(dto.getStatus());
        doc.setRemarks(dto.getRemarks());
        doc.setUploadedAt(dto.getUploadedAt());

        return doc;
    }

    public static DocumentDto entityToDto(Document doc) {

        DocumentDto dto = new DocumentDto();
        dto.setId(doc.getId());
        dto.setStudentId(doc.getStudentId());
        dto.setDocumentType(doc.getDocumentType());
        dto.setStatus(doc.getStatus());
        dto.setRemarks(doc.getRemarks());
        dto.setUploadedAt(doc.getUploadedAt());

        return dto;
    }
}
