package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.email.VerificationEmail;
import com.elroykanye.istudybucket.business.service.MailContentBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
public class MailContentBuilderImpl implements MailContentBuilder {
    private final Configuration configuration;

    @Override
    public String build() throws IOException, TemplateException {
        Map<String, String> messageModel = new HashMap<>();
        StringWriter stringWriter = new StringWriter();
        configuration.getTemplate("mail.ftl").process(messageModel, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    public String buildVerificationEmailContent(VerificationEmail verificationEmail) throws IOException, TemplateException {
        Map<String, String> messageModel =  new HashMap<>();
        StringWriter stringWriter = new StringWriter();
        messageModel.put("message", verificationEmail.getMessage());
        messageModel.put("verificationUrl", verificationEmail.getVerificationUrl());
        messageModel.put("recipientName", verificationEmail.getRecipientName());

        configuration.getTemplate("verify-email.ftl").process(messageModel, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
