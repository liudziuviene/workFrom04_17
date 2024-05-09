package org.example.workfrom0417.task1and2.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateSchoolDTO {

    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 3, max = 100, message = "{validation.constraints.size.message}")
    private String title;
    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 3, max = 100, message = "{validation.constraints.size.message}")
    private String address;
    @NotNull(message = "{validation.constraints.not.empty.message}")
    @Positive(message = "{validation.constraints.positive.message}")
    private int numberOfStudents;
    @NotNull(message = "{validation.constraints.not.empty.message}")
    @Positive(message = "{validation.constraints.positive.message}")
    private int numberOfTeachers;
    @NotNull(message = "{validation.constraints.not.empty.message}")
    @Positive(message = "{validation.constraints.positive.message}")
    private int numberOfClasses;
}
