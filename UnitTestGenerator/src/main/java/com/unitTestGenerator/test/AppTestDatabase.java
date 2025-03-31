package com.unitTestGenerator.test;

import java.util.List;

import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.EndebleIOC;
import com.unitTestGenerator.persistence.mapper.DataDaoMapper;
import com.unitTestGenerator.persistence.model.DataPojo;
import com.unitTestGenerator.persistence.repositories.DataDAOServicesImplement;
import com.unitTestGenerator.persistence.model.Data;
import com.unitTestGenerator.persistence.repositories.IDaoService;

@EndebleIOC("com.unitTestGenerator")
public class AppTestDatabase {

    public static IDaoService dao;
    private static DataDaoMapper mapper;

    public AppTestDatabase(IDaoService dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {
        IDaoService dao = ContextIOC.getInstance(AppTestDatabase.class).getClassInstance(DataDAOServicesImplement.class);


        DataPojo pancho1 = new DataPojo( "pancho1", "vinayak@sqs.com", "public");
        DataPojo pancho2 = new DataPojo( "pancho2", "vinayak@sqs.com", "panchossssss");

        dao.save(pancho1);
        dao.save(pancho2);

        List<DataPojo> all = dao.findAll();
        for (DataPojo contact : all) {
            System.out.println("all = Id: " + contact.getId() + " | Name:" + contact.getName() + " | datos:" + contact.getDatos());
        }

//        List<Data> pancho = dao.findByName("pancho2");
//
//        for (Data contact : pancho) {
//            System.out.println("findByName list = Id: " + contact.getId() + " | Name:" + contact.getName() + " | datos:" + contact.getDatos());
//        }

//        List<Data> id2 = dao.findById2(2l);
//        DataPojo id = dao.findById(2l);

//        for (Data contact : id2) {
//            System.out.println("id2 object = Id: " + contact.getId() + " | Name:" + contact.getName() + " | datos:" + contact.getDatos());
//        }
//        System.out.println("id object = Id: " + id.getId() + " | Name:" + id.getName() + " | datos:" + id.getDatos());
//        id.setName("SUPERCALIFRAJILISTICO");
//        dao.update(id);
        DataPojo id3 = dao.findById(2l);
        System.out.println("id object = Id: " + id3.getId() + " | Name:" + id3.getName() + " | datos:" + id3.getDatos());
    }
}