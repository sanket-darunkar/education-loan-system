package com.els.educationloansystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegisterDTO {

    private String name;
    private String email;
    private String mobile;
    private String password;

   


}
