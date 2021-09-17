package com.feljtech.istudybucket.dto.email;


import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerificationEmail extends DefaultEmail {
    private String verificationUrl;
    private String recipientName;
}
