package org.example.workfrom0417.task1and2.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class BaseErrorResponse {
    private String message;
    private int status;

    public BaseErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;

        log.warn("Error occured: {}", this);
    }

    public BaseErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
