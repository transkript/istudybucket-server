package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.entity.model.NotificationEmail;

public interface MailService {
    void sendEmail(NotificationEmail notificationEmail) ;
}
