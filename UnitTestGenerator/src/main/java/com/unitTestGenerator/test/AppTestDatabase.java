package com.unitTestGenerator.test;

import java.util.List;
import com.unitTestGenerator.persistence.repositories.DataDAOServicesImplement;
import com.unitTestGenerator.persistence.model.Data;
import com.unitTestGenerator.persistence.repositories.IDaoService;


public class AppTestDatabase {

    public static void main(String[] args) {
        IDaoService dao = new DataDAOServicesImplement();
        List<Data> all = dao.findAll();

        for (Data contact : all) {
            System.out.println("all = Id: " + contact.getId() + " | Name:" + contact.getName() + " | datos:" + contact.getDatos());
        }

//        Data pancho1 = new Data( "pancho1", "vinayak@sqs.com", "public");
//        Data pancho2 = new Data( "pancho2", "vinayak@sqs.com", "panchossssss");

//        dao.save(pancho1);
//        dao.save(pancho2);

//        List<Data> pancho = dao.findByName("pancho2");
//
//        for (Data contact : pancho) {
//            System.out.println("findByName list = Id: " + contact.getId() + " | Name:" + contact.getName() + " | datos:" + contact.getDatos());
//        }

//        List<Data> id2 = dao.findById2(2l);
        Data id = dao.findById(2l);

//        for (Data contact : id2) {
//            System.out.println("id2 object = Id: " + contact.getId() + " | Name:" + contact.getName() + " | datos:" + contact.getDatos());
//        }
        System.out.println("id object = Id: " + id.getId() + " | Name:" + id.getName() + " | datos:" + id.getDatos());
        id.setName("SUPERCALIFRAJILISTICO");
        dao.update(id);
        Data id3 = dao.findById(2l);
        System.out.println("id object = Id: " + id3.getId() + " | Name:" + id3.getName() + " | datos:" + id3.getDatos());
    }
}