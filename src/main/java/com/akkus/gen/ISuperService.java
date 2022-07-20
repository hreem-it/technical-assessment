/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akkus.gen;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.unmodifiableList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Abdurrahman AKKUŞ
 * @param <T> extends ISuperEntity
 */
public abstract class ISuperService<T extends ISuperEntity> {
    
    List<ISuperEntity> superEntities = new ArrayList();
    ISuperEntity superEntity;

    /**
     * This function gets all entities from the counterpart table
     * @param superEntity (which is an interface)
     * @return List of entities
     */
    public List<ISuperEntity> getAllEntities(ISuperEntity superEntity) {

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(superEntity.getClass())
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //CRUD 
            superEntities = session
                    .createQuery("FROM " + superEntity.getClass().getSimpleName()).getResultList();

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
        return unmodifiableList(superEntities);
    }

    /**
     *
     * @param superEntity
     * @param kosullar
     * @param siralama
     * @return
     */
    public ISuperEntity getSingleEntity(ISuperEntity superEntity, String kosullar, String siralama) {

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(superEntity.getClass())
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            siralama = siralama.isEmpty() ? "" : " ORDER BY " + siralama;
            //CRUD 
            superEntity = (ISuperEntity) session
                    .createQuery("FROM " + superEntity.getClass().getSimpleName()
                            + " WHERE " + kosullar +" "+ siralama).getSingleResult();
            session.getTransaction().commit();

        } catch (Exception e) {
// Hiç bir sonuç dönmemesi durumunda yapılan exception handling
                        return null;
        } finally {
            factory.close();
        }
        return superEntity;
    }

    /**
     *
     * @param superEntity
     * @param kosullar
     * @return
     */
    public List<ISuperEntity> getAllEntitiesBy(ISuperEntity superEntity, String kosullar) {

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(superEntity.getClass())
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //CRUD 
            superEntities = session
                    .createQuery("FROM " + superEntity.getClass().getSimpleName()
                            + " WHERE " + kosullar).getResultList();

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
        return unmodifiableList(superEntities);
    }
    
    /**
     *
     * @param newEntity
     * @return
     */
    public int newEntity(ISuperEntity newEntity) {
        int yeniId = 0;
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(newEntity.getClass())
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            session.save(newEntity);

            yeniId = newEntity.getId();

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
        return yeniId;
    }

    /**
     *
     * @param id
     * @param newEntity
     * @return
     */
    public int deleteEntity(int id, ISuperEntity newEntity) {
        int status = 0;
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(newEntity.getClass())
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //CRUD 
            ISuperEntity superEntity = session.get(newEntity.getClass(), id);
            session.delete(superEntity);

            session.getTransaction().commit();
            status = 1;
        } finally {
            factory.close();
        }
        return status;
    }

    /**
     *
     * @param id
     * @param newEntity
     * @return
     */
    public abstract int updateEntity(int id, ISuperEntity newEntity);
}
