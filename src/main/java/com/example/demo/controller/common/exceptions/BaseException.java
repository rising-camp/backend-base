package com.example.demo.controller.common.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private HttpStatus status;

    public BaseException(HttpStatus status) {
        super(status.getReasonPhrase());
        this.status = status;
    }

    public BaseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public BaseException(HttpStatus status, Exception exception) {
        super(status.getReasonPhrase(), exception);
        this.status = status;
    }

    public BaseException(HttpStatus status, String message, Exception exception) {
        super(message, exception);
        this.status = status;
    }
}
