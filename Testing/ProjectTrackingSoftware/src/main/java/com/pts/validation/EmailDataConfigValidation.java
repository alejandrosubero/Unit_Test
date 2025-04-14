package com.pts.validation;


import com.pts.pojo.EmailDataConfigPojo;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
public class EmailDataConfigValidation {

    public EmailDataConfigPojo valida(EmailDataConfigPojo emailconfig) {
        EmailDataConfigPojo pojo = null;
        try {
            if (emailconfig != null) {
                if (
                        emailconfig.getHost() != null &&
                        emailconfig.getMailPassword() != null &&
                        emailconfig.getMailUsername() != null
                ) {
                    pojo = emailconfig;
                }
            }
            return pojo;
        } catch (Exception e) {
            e.printStackTrace();
            return pojo;
        }
    }

    public Long valida_id(String poder) {
        Long id_Delete = 0l;
        try {
            if (poder != null) {
                if (poder.length() > 0 && !Pattern.matches("[a-zA-Z]+", poder)) {
                    id_Delete = Long.parseLong(poder);
                }
            }
            return id_Delete;
        } catch (Exception e) {
            e.printStackTrace();
            return id_Delete;
        }
    }

    public <T> Object validation(T t) {
        T elemento = null;
        try {
            if (t != null) {
                elemento = t;
            }
            return elemento;
        } catch (Exception e) {
            e.printStackTrace();
            return elemento;
        }
    }

}
