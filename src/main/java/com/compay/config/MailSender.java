package com.compay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSender {
    @Configuration
    public class MailConfig {

        @Bean
        public JavaMailSender javaMailService() {
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost("smtp.gmail.com");
            javaMailSender.setPort(587);
            javaMailSender.setUsername("compayhillel@gmail.com");
            javaMailSender.setPassword("compayHillelcompayHillel");
            javaMailSender.setJavaMailProperties(getMailProperties());
            return javaMailSender;
        }

        private Properties getMailProperties() {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.transport.protocol", "smtp");
            properties.setProperty("mail.debug", "true");
            return properties;
        }
    }
}
