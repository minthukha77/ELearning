package com.blissstock.mappingSite.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class GmailConfig {

  @Bean("gmail")
  public JavaMailSender gmailMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);

    mailSender.setUsername("sys@pyinnyar-subuu.com");
    mailSender.setPassword("Bs_pyinnyar01");

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "false");

    return mailSender;
  }

  // test impl

  // @Bean
  // public ResourceBundleMessageSource emailMessageSource() {
  // final ResourceBundleMessageSource messageSource = new
  // ResourceBundleMessageSource();
  // messageSource.setBasename("mail/MailMessages");
  // return messageSource;
  // }

}
