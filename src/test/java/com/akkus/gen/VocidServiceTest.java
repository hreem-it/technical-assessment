
package com.akkus.gen;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.runners.MethodSorters.JVM;


@FixMethodOrder(JVM)
public class VocidServiceTest {

    /**
     *
     */
    public static int ID;

    /**
     *
     */
    public static VocidEntity VOCID_ENTITY;
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    public VocidServiceTest() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    /**
     *
     */
    
    @Test
    public void newEntity(){
        VocidService vService = new VocidService();
        VOCID_ENTITY=new VocidEntity();
        VOCID_ENTITY.setAge(10);
        VOCID_ENTITY.setHospital("antalya");
        VOCID_ENTITY.setName("ahmet");
        VOCID_ENTITY.setSurname("black");
        VOCID_ENTITY.setSymptoms("cold");
        VOCID_ENTITY.setSsn(500);

        ID= vService.newEntity(VOCID_ENTITY);
        assertTrue(ID>0);
    }
    
    /**
     *
     */
    @Test
    public void getEntity(){
        VocidService vService = new VocidService();
        VOCID_ENTITY = (VocidEntity) vService.getSingleEntity(new VocidEntity(), " id="+ID, "'ASC'");
        assertTrue(VOCID_ENTITY.getId()==ID);
    }
    
    /**
     *
     */
    @Test
    public void updateEntity(){
        VocidService vService = new VocidService();
        VOCID_ENTITY.setName("ali");
        int status= vService.updateEntity(VOCID_ENTITY.getId(), VOCID_ENTITY);
        assertTrue(status==1);
    }
    
    /**
     *
     */
    @Test
    public void getEntityByHospital(){
        VocidService vService = new VocidService();
        List foundEntities =  vService
                .getAllEntitiesBy(new VocidEntity(), "hospital='" + VOCID_ENTITY.getHospital() + "'");
        assertNotNull(foundEntities);
    }
    
    /**
     *
     */
    @Test
    public void getEntityBySymptom(){
        VocidService vService = new VocidService();
        List foundEntities = vService
                .getAllEntitiesBy(new VocidEntity(), "symptoms='" + VOCID_ENTITY.getSymptoms() + "'");
        assertNotNull(foundEntities);
    }
    
    /**
     *
     */
    @Test
    public void getAllEntities(){
        VocidService vService = new VocidService();
        List foundEntities =  vService.getAllEntities(new VocidEntity());
        assertNotNull(foundEntities);
    }    
    
    /**
     *
     */
    @Test
    public void deleteEntity(){
        VocidService vService = new VocidService();
        int status = vService.deleteEntity(VOCID_ENTITY.getId(), new VocidEntity());
        assertTrue(status==1);
    }
}
