package com.pts.mapper;

import com.pts.entitys.EmailDataConfig;
import com.pts.pojo.EmailDataConfigPojo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailDataConfigMapper {

    public EmailDataConfig pojoToEntity(EmailDataConfigPojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        EmailDataConfig entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, EmailDataConfig.class);
        }
        return entity;
    }

    public EmailDataConfigPojo entityToPojo(EmailDataConfig entity) {
        ModelMapper modelMapper = new ModelMapper();
        EmailDataConfigPojo pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, EmailDataConfigPojo.class);
        }
        return pojo;
    }


    public List<EmailDataConfigPojo> listEntityToPojo(List<EmailDataConfig> Lista){
        List<EmailDataConfigPojo> listPojo = new ArrayList<EmailDataConfigPojo>();
        if (Lista != null && Lista.size() >0){
            for (EmailDataConfig entity : Lista){
                listPojo.add( entityToPojo(entity));
            }
        }
        return  listPojo;
    }


    public List<EmailDataConfig> listPojoToEntity(List<EmailDataConfigPojo> Lista){
        List<EmailDataConfig> listEntity = new ArrayList<EmailDataConfig>();
        if (Lista != null && Lista.size() >0){
            for (EmailDataConfigPojo pojo : Lista){
                listEntity.add( pojoToEntity(pojo));
            }
        }
        return  listEntity;
    }


}
