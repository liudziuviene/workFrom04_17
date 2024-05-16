package org.example.workfrom0417.task1and2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateSchoolTitleDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String title;
}
