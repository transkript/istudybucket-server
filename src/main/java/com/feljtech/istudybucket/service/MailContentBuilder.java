package com.feljtech.istudybucket.service;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface MailContentBuilder {
    String build(String message) throws IOException, TemplateException;
}
