package com.feljtech.istudybucket.dto.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DefaultEmail {
    private String recipient;
    private String subject;
    private String message;
}
