package com.pts.service;

import com.pts.pojo.EmailPojo;

import java.util.List;

public interface MailServices {

    public Boolean sendMail(EmailPojo email, String portMail);
    public Boolean sendMails(List<EmailPojo> email, String portMail);


}
