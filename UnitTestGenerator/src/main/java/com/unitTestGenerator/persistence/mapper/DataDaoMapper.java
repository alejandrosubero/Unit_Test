package com.unitTestGenerator.persistence.mapper;

import com.unitTestGenerator.persistence.model.Data;
import com.unitTestGenerator.persistence.model.DataPojo;

import java.util.ArrayList;
import java.util.List;

public class DataDaoMapper {


    public List<Data> listPojoToListEntity(List<DataPojo> pojos) {
        List<Data> entitys = new ArrayList<>();
        if (pojos != null && pojos.size() > 0) {
            pojos.forEach(pojo -> entitys.add(this.pojoToEntity(pojo)));
        }
        return entitys;
    }

    public Data pojoToEntity(EmpleadoPojo pojo) {
//        ModelMapper modelMapper = new ModelMapper();
        Data entity = null;
        if (pojo != null) {
            entity = new ModelMapper().map(pojo, Empleado.class);
        }
        return entity;
    }

    public DataPojo entityToPojo(Data entity) {
//        ModelMapper modelMapper = new ModelMapper();
        DataPojo pojo = null;
        if (entity != null) {
            pojo = new ModelMapper().map(entity, EmpleadoPojo.class);
        }
        return pojo;
    }

}
