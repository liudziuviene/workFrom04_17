package org.example.workfrom0417.dto;

import lombok.Data;

@Data
public class CreateSchoolDTO {
    private String title;
    private String address;
    private int numberOfStudents;
    private int numberOfTeachers;
    private int numberOfClasses;
}
