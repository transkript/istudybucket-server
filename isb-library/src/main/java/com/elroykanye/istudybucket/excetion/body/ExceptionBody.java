package com.elroykanye.istudybucket.excetion.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

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
    @JsonProperty("path")
    private String path;

    public static ExceptionBody buildExceptionBody(RuntimeException exception, WebRequest webRequest, HttpStatus httpStatus, List<String> errors) {
        return ExceptionBody.builder()
                .message(exception.getMessage())
                .timestamp(Instant.now())
                .status(httpStatus)
                .errors(errors)
                .path(webRequest.getContextPath())
                .build();
    }
}
