package com.unitTestGenerator.test;

import java.util.List;
import java.util.Properties;

import com.unitTestGenerator.persistence.DataDAO;
import com.unitTestGenerator.persistence.model.Data;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;


public class AppTestDatabase {

    public static void main(String[] args) {
        DataDAO dao = new DataDAO();
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


//        private static SessionFactory sessionFactory = null;
//        private static ServiceRegistry serviceRegistry = null;
//
//        private static SessionFactory configureSessionFactory() throws HibernateException {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//
//            Properties properties = configuration.getProperties();
//
//            serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//
//            return sessionFactory;
//        }
//
//        public static void main(String[] args) {
//            // Configure the session factory
//            configureSessionFactory();
//
//            Session session = null;
//            Transaction tx = null;
//
//            try {
//                session = sessionFactory.openSession();
//                tx = session.beginTransaction();
//
//                // Creating Contact entity that will be save to the sqlite database
//                Data myContact = new Data(1l, "Vinayak", "vinayak@sqs.com", "public");
//                Data yourContact = new Data(2l, "Likhit", "likhit@email.com", "private");
//                Data myContactpx = new Data( "Vinayak", "vinayak@sqs.com", "public");
//                // Saving to the database
////                session.update(myContact);
////                session.save(myContact);
////                session.save(yourContact);
//                session.save(myContactpx);
//
//                // Committing the change in the database.
//                session.flush();
//                tx.commit();
//
//                // Fetching saved data
//                List<Data> contactList = session.createQuery("from Data").list();
//
//                for (Data contact : contactList) {
//                    System.out.println("Id: " + contact.getId() + " | Name:"  + contact.getName() + " | datos:" + contact.getDatos());
//                }
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                // Rolling back the changes to make the data consistent in case of any failure
//                // in between multiple database write operations.
//                tx.rollback();
//            } finally{
//                if(session != null) {
//                    session.close();
//                }
//            }
//        }
}
