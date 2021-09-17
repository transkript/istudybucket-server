package com.feljtech.istudybucket.service.impl;

import com.feljtech.istudybucket.entity.model.NotificationEmail;
import com.feljtech.istudybucket.excetion.IstudybucketException;
import com.feljtech.istudybucket.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private final Configuration configuration;
    private final JavaMailSender javaMailSender;


    public void sendEmail(NotificationEmail notificationEmail) {

        MimeMessagePreparator mimeMessagePreparator = message -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
            mimeMessageHelper.setFrom("elroykanye@gmail.com");
            mimeMessageHelper.setSubject(notificationEmail.getSubject());
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            String emailContent = this.buildEmail(notificationEmail.getBody());
            mimeMessageHelper.setText(emailContent, true);
        };
        try {
            javaMailSender.send(mimeMessagePreparator);
            log.info("Activation Email Sent!");
        } catch(MailException exception) {
            throw new IstudybucketException("Activation Email not sent");
        }
    }

    public String buildEmail(String message) throws IOException, TemplateException {
        Map<String, String> messageModel = new HashMap<>();
        StringWriter stringWriter = new StringWriter();
        messageModel.put("message", message);
        configuration.getTemplate("mail.ftl").process(messageModel, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
