package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.email.NotificationEmail;

public interface MailService {
    void sendEmail(NotificationEmail notificationEmail) ;
}
