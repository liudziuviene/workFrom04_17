package org.example.workfrom0417.task1and2.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundErrorResponse extends BaseErrorResponse {

    public NotFoundErrorResponse(String entityName, String criteriaName, String criteriaValue) {
        super(HttpStatus.NOT_FOUND.value(),
                String.format("Entity %s not found by %s: %s", entityName, criteriaName, criteriaValue));
    }

    public NotFoundErrorResponse() {
    }
}
