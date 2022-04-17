package com.feljtech.istudybucket.excetion.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionBody {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private HttpStatus status;
    @JsonProperty("timestamp")
    private Instant timestamp;
    @JsonProperty("errors")
    private List<String> errors;

    public static ExceptionBody buildExceptionBody(RuntimeException exception, HttpStatus httpStatus, List<String> errors) {
        return ExceptionBody.builder()
                .message(exception.getMessage())
                .timestamp(Instant.now())
                .status(httpStatus)
                .errors(errors)
                .build();
    }
}
