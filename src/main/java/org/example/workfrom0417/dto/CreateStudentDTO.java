package org.example.workfrom0417.dto;

import lombok.Data;

@Data
public class CreateStudentDTO {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
}
