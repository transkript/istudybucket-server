package com.feljtech.istudybucket.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NotificationEmail {
    private String recipient;
    private String subject;
    private String body;

}
