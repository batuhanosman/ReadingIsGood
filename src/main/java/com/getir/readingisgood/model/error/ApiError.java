package com.getir.readingisgood.model.error;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private String exceptionMessage;
    HttpStatus status;
    LocalDateTime timestamp;

    public ApiError(String exceptionMessage, HttpStatus status, LocalDateTime timestamp) {
        this.exceptionMessage = exceptionMessage;
        this.status = status;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "[exceptionMessage=" + getExceptionMessage() + ", status=" + getStatus()
                + ", timestamp=" + getTimestamp() + "]";
    }

}

