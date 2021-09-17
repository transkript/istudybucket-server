package com.feljtech.istudybucket.dto.email;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NotificationEmail implements DefaultEmail {
    private String recipient;
    private String subject;
    private String body;

}
