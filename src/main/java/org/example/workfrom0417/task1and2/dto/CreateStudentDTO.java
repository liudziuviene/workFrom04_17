package org.example.workfrom0417.task1and2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateStudentDTO {

    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 2, max = 50, message = "{validation.constraints.size.message}")
    private String name;
    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 2, max = 50, message = "{validation.constraints.size.message}")
    private String surname;
    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 8, max = 50, message = "{validation.constraints.size.message}")
    private String email;
    @NotNull(message = "{validation.constraints.not.empty.message}")
    @Positive(message = "{validation.constraints.positive.message}")
    @Size(min = 2, max = 20, message = "{validation.constraints.size.message}")
    private String phone;
    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 2, max = 100, message = "{validation.constraints.size.message}")
    private String address;
}
