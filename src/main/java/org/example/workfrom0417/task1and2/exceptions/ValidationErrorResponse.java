package org.example.workfrom0417.task1and2.exceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse extends BaseErrorResponse {
    private List<String> errors;

    public ValidationErrorResponse(List<FieldError> errors) {
        super("Validation error", HttpStatus.BAD_REQUEST.value());

        this.errors = new ArrayList<>();

        for (FieldError error : errors) {

            this.errors.add(String.format("%s: %s", error.getField(), error.getDefaultMessage()));
        }
    }
}
