package com.els.educationloansystem.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentNotificationDto {

    private String message;
    private String type; // INFO, SUCCESS, WARNING, ERROR
    private LocalDate date;
}
