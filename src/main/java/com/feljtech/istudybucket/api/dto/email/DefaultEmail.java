package com.feljtech.istudybucket.api.dto.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DefaultEmail {
    protected String recipient;
    protected String subject;
    protected String message;
}
