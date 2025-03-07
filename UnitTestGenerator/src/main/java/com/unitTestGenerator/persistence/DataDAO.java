package com.unitTestGenerator.persistence;

import com.unitTestGenerator.persistence.model.Data;

import javax.persistence.*;
import java.util.List;

public class DataDAO {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        try {
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("sqlitePU");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void aydata() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        System.out.println(em.createQuery("SELECT COUNT(d) FROM Data d", Long.class).getSingleResult());
        em.close();
    }

    public void save(Data data) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(data);
            et.commit();
        } catch (Exception e) {
            if (et != null) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }



    public Data findByName(String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT d FROM Data d WHERE TRIM(d.name) = TRIM(:name)", Data.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Data> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            List<Data> dataList = em.createQuery("SELECT d FROM Data d", Data.class).getResultList();
            System.out.println("Number of results: " + dataList.size());
            for (Data data : dataList) {
                System.out.println("Data: " + data);
            }
            return dataList;
        } finally {
            em.close();
        }
    }

    public void update(Data data) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.merge(data);
            et.commit();
        } catch (Exception e) {
            if (et != null) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Data data = em.find(Data.class, id);
            if (data != null) {
                em.remove(data);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}