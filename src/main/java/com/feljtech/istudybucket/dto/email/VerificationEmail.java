package com.feljtech.istudybucket.dto.email;


import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VerificationEmail extends DefaultEmail {
    private String verificationUrl;
    private String recipientName;
}
