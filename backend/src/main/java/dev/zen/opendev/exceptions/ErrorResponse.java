package dev.zen.opendev.exceptions;

import java.time.Instant;
import org.springframework.http.HttpStatus;

public record ErrorResponse(String message, Instant createdAt, int code, String status) {
    public ErrorResponse(String message, HttpStatus status) {
        this(message, Instant.now(), status.value(), status.name());
    }
}
