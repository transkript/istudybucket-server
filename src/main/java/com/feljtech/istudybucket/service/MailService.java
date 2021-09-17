package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.email.VerificationEmail;

public interface MailService {

    void sendVerificationEmail(VerificationEmail verificationEmail);

}
