/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akkus.gen;

import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

/**
 *
 * @author Abdurrahman AKKUŞ
 */
@Service
public class VocidService extends ISuperService<ISuperEntity> {

    /**
     *
     * @param id
     * @param newEntity
     * @return
     */
    @Override
    public int updateEntity(int id, ISuperEntity newEntity) {
        int successStatus = 0;
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(VocidEntity.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            VocidEntity newVocid = (VocidEntity) newEntity;

            //CRUD 
            VocidEntity oldVocid = session.get(VocidEntity.class, id);
            oldVocid.setAge(newVocid.getAge());
            oldVocid.setHospital(newVocid.getHospital());
            oldVocid.setName(newVocid.getName());
            oldVocid.setSurname(newVocid.getSurname());
            oldVocid.setSsn(newVocid.getSsn());
            oldVocid.setSymptoms(newVocid.getSymptoms());
            
            session.save(oldVocid);

            session.getTransaction().commit();
            successStatus = 1;

        } finally {
            factory.close();
        }
        return successStatus;
    }
    private static final Logger LOG = Logger.getLogger(VocidService.class.getName());

}
