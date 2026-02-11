package com.els.educationloansystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminApplicationsTrendDto {

    private String date;       // yyyy-MM-dd
    private long applications; // count
}
