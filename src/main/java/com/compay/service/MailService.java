package com.compay.service;

import com.compay.entity.User;

public interface MailService {

    public void sendEmail(final Object object);

    public void sendRecoveryMail(User user);
}
