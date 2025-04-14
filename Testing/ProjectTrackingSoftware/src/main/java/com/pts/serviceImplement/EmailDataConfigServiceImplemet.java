package com.pts.serviceImplement;

import com.pts.entitys.EmailDataConfig;
import com.pts.mapper.EmailDataConfigMapper;
import com.pts.pojo.EmailDataConfigPojo;
import com.pts.repository.EmailDataConfigRepositoriy;
import com.pts.security.EncryptAES;
import com.pts.service.EmailDataConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailDataConfigServiceImplemet implements EmailDataConfigService {

    //Todo: mejorar la frase de encriptado.
    @Value("${saltAESKey}")
    private String saltAES;

    @Autowired
    private EmailDataConfigMapper mapper;

    @Autowired
    private EmailDataConfigRepositoriy repositoriy;

    @Autowired
    private EncryptAES encryptAES;


    @Override
    public Boolean saveEmailDataConfigService(EmailDataConfig config) {
        try {
            String pass = encryptAES.encript(config.getMailPassword() ,saltAES);
            String userName = encryptAES.encript(config.getMailUsername(),saltAES);
            config.setMailPassword(pass);
            config.setMailUsername(userName);
            repositoriy.save(config);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public EmailDataConfigPojo findByIdEmailConfig(Long id) {
        return mapper.entityToPojo(repositoriy.findByIdEmailConfig(id));
    }

    @Override
    public EmailDataConfigPojo findByPort(String port) {
        return mapper.entityToPojo(repositoriy.findByPort(port).get());
    }

    @Override
    public EmailDataConfigPojo findByMailUsername(String username) {
        return mapper.entityToPojo(repositoriy.findByMailUsername(username));
    }

    @Override
    public boolean deleteEmailDataConfig(Long id) {
        repositoriy.deleteById(id);
        return true;
    }

    @Override
    public void deleteEmailDataConfig(String username) {
        this.deleteEmailDataConfig(this.findByMailUsername(username).getIdEmailConfig());
    }

    @Override
    public List<EmailDataConfigPojo> getAll() {
        List<EmailDataConfigPojo> lista = new ArrayList<EmailDataConfigPojo>();
        repositoriy.findAll().forEach(EmailDataConfig -> lista.add(mapper.entityToPojo(EmailDataConfig)));
        return lista;
    }

    @Override
    public List<EmailDataConfigPojo> findByMailUsernameContaining(String keyboard) {
        return mapper.listEntityToPojo(repositoriy.findByMailUsernameContaining(keyboard));
    }
}
