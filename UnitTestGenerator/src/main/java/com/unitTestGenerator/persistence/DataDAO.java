package com.unitTestGenerator.persistence;

import com.unitTestGenerator.persistence.model.Data;

import com.unitTestGenerator.persistence.model.Data;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Properties;

public class DataDAO {

    public DataDAO() {
        configureSessionFactory();
    }

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        Properties properties = configuration.getProperties();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }


    public void update(Data data) {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(data);
            // Committing the change in the database.
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        } finally{
            if(session != null) {
                session.close();
            }
        }
    }



        public void save(Data data) {
            Session session = null;
            Transaction tx = null;

            try {
                session = sessionFactory.openSession();
                tx = session.beginTransaction();
                session.save(data);
                // Committing the change in the database.
                session.flush();
                tx.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                // Rolling back the changes to make the data consistent in case of any failure
                // in between multiple database write operations.
                tx.rollback();
            } finally{
                if(session != null) {
                    session.close();
                }
            }
        }

    public List<Data> findAll() {
        Session session = null;
        Transaction tx = null;
        List<Data> list = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            list = session.createQuery("from Data").list();
        } catch (Exception ex) {
            ex.printStackTrace();
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        } finally{
            if(session != null) {
                session.close();
            }
        }
        return list;
    }


        public Data findById(Long id) {
            Session session = null;
            Transaction tx = null;
            Data data = null;
            try {
                session = sessionFactory.openSession();
                tx = session.beginTransaction();
                String idS = String.valueOf(id);
                data = (Data) session.get(Data.class,id);
                // Commit de la transacción
                tx.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                // Rolling back the changes to make the data consistent in case of any failure
                // in between multiple database write operations.
                tx.rollback();
            } finally{
                if(session != null) {
                    session.close();
                }
            }
            return data;
        }


    public List<Data> findById2(Long id) {
        Session session = null;
        Transaction tx = null;
        List<Data> data = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Realizar una consulta HQL
            String hql = "FROM Data WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            data = query.list();  // Asigna el resultado a la variable 'data'

            // Commit de la transacción
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();  // Asegúrate de que tx no sea null antes de hacer rollback
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return data;  // Retorna 'data' que ahora contiene el resultado de la consulta
    }


    public List<Data> findByName(String name) {
        Session session = null;
        Transaction tx = null;
        List<Data>  data = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Realizar una consulta HQL
            String hql = "FROM Data WHERE name = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            data = query.list();
            // Commit de la transacción
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        } finally{
            if(session != null) {
                session.close();
            }
        }
        return data;
    }

        public void delete(Data data) {

        }
    }
