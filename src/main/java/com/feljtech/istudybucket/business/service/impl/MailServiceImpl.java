package com.feljtech.istudybucket.service.impl;

import com.feljtech.istudybucket.dto.email.VerificationEmail;
import com.feljtech.istudybucket.enums.Email;
import com.feljtech.istudybucket.excetion.IstudybucketException;
import com.feljtech.istudybucket.service.MailContentBuilder;
import com.feljtech.istudybucket.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;


    @Override
    public void sendVerificationEmail(VerificationEmail verificationEmail) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(String.valueOf(Email.DEFAULT_FROM)); // set the email from to the default application from address
            mimeMessageHelper.setSubject(verificationEmail.getSubject()); // set the email subject
            mimeMessageHelper.setTo(verificationEmail.getRecipient()); // set the email recipient

            String emailContent = mailContentBuilder.buildVerificationEmailContent(verificationEmail);
            mimeMessageHelper.setText(emailContent, true);
        };
        try {
            javaMailSender.send(mimeMessagePreparator);
            log.info("Verification email sent");
        } catch(MailException mailException) {
            log.error("Verification email not sent");
            throw new IstudybucketException("Could not send email");
        }


    }

}
