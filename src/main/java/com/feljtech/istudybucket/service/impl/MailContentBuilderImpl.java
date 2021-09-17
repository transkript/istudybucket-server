package com.feljtech.istudybucket.service.impl;

import com.feljtech.istudybucket.service.MailContentBuilder;
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
    public String build(String message) throws IOException, TemplateException {
        Map<String, String> messageModel = new HashMap<>();
        StringWriter stringWriter = new StringWriter();
        configuration.getTemplate("mail.ftl").process(messageModel, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
