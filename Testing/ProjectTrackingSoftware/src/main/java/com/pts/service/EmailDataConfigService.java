package com.pts.service;

import com.pts.entitys.EmailDataConfig;
import com.pts.pojo.EmailDataConfigPojo;

import java.util.List;
import java.util.Optional;

public interface EmailDataConfigService {

    public Boolean saveEmailDataConfigService(EmailDataConfig config);
    public EmailDataConfigPojo findByIdEmailConfig(Long id);
    public EmailDataConfigPojo findByPort(String port);
    public EmailDataConfigPojo findByMailUsername(String username);
    public boolean deleteEmailDataConfig(Long id);
    public void deleteEmailDataConfig(String username);
    public List<EmailDataConfigPojo> getAll();
    public List<EmailDataConfigPojo>  findByMailUsernameContaining(String keyboard);
}
