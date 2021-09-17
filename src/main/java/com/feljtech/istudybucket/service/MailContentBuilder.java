package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.email.VerificationEmail;
import freemarker.template.TemplateException;

import java.io.IOException;

public interface MailContentBuilder {
    String build() throws IOException, TemplateException;

    String buildVerificationEmailContent(VerificationEmail verificationEmail) throws IOException, TemplateException;
}
