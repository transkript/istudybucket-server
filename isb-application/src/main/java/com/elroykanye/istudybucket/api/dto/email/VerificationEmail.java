package com.elroykanye.istudybucket.api.dto.email;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VerificationEmail {
    protected String recipient;
    protected String subject;
    protected String message;
    private String verificationUrl;
    private String recipientName;
}
