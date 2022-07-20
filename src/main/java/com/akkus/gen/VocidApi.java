/*
 * Copyright (C) 2019 Abdurrahman AKKUŞ <iletisim@algoritimbilisim.com>
 * Bu yazılımın tüm hakları Algoritim Bilişim'e aittir.
 */
package com.akkus.gen;

import com.akkus.gen.util.ResponseEntityUtil;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This API provides CRUD operations for Vocid data.
 * 
 * @author Abdurrahman AKKUŞ
 */
@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders = "*")
@RequestMapping("/vocid")
public class VocidApi implements ISuperApi<VocidEntity> {

    @Autowired
    VocidService vocidService;

    @Autowired
    ResponseEntityUtil responseEntityUtil;
    
    /**
     * Gets all Vocid data
     * @return List of Vocid data as JSON format
    */
    @GetMapping(
            produces = {
                APPLICATION_JSON_UTF8_VALUE})
    @Override
    public List<VocidEntity> getAll() {
        return (List<VocidEntity>) (List<?>) new VocidService()
                .getAllEntities(new VocidEntity());
    }

    
    /**
     * Gets all Vocid data rely on symptoms
     * @param symptoms
     * @return List of restricted Vocid data as JSON format
    */
    @GetMapping(
            path = "/symptom/{symptoms}",
            produces = {
                APPLICATION_JSON_UTF8_VALUE})
    public List<VocidEntity> getAllBySymptom(@PathVariable String symptoms) {
        return (List<VocidEntity>) (List<?>) vocidService
                .getAllEntitiesBy(new VocidEntity(), "symptoms='" + symptoms + "'");
    }

    /**
     * Gets all Vocid data rely on hospitals
     * @param hospital
     * @return List of restricted Vocid data as JSON format
    */
    @GetMapping(
            path = "/hospital/{hospital}",
            produces = {
                APPLICATION_JSON_UTF8_VALUE})
    public List<VocidEntity> getAllByHospital(@PathVariable String hospital) {
        return (List<VocidEntity>) (List<?>) vocidService
                .getAllEntitiesBy(new VocidEntity(), "hospital='" + hospital + "'");
    }

    
    /**
     * New Vocid data is enrolled by this function
     * @param newEntity
     * VocidEntity. Valid JSON data must be send.
     * 
     * @return Id value of new entity returns
    */
    @PostMapping(
            consumes = {
                APPLICATION_JSON_UTF8_VALUE},
            produces = {
                APPLICATION_JSON_UTF8_VALUE})
    @Override
    public ResponseEntity<List<VocidEntity>> setNew(@Valid @RequestBody VocidEntity newEntity) {
        int successStatus = vocidService.newEntity(newEntity);
        return responseEntityUtil.getResponse(successStatus);
    }

    
    /**
     * Vocid data is deleted by this function rely on id value
     * @param id
     * 
     * @return Status return. If the result is affirmative then 1 returns, 
     * otherwise 0 returns 
    */
    @DeleteMapping(path = "/{id}")
    @Override
    public ResponseEntity delete(@PathVariable int id) {
        int successStatus = vocidService.deleteEntity(id, new VocidEntity());
        return responseEntityUtil.getResponse(successStatus);
    }

    /**
     * New Vocid data is enrolled by this function
     * @param id
     * @param newEntity 
     * id, VocidEntity. Valid JSON data must be send. 
     * 
     * @return  Status return. If the result is affirmative then 1 returns, 
     * otherwise 0 returns 
    */
    @PutMapping(path = "/{id}")
    @Override
    public ResponseEntity update(
            @PathVariable int id,
            @Valid @RequestBody VocidEntity newEntity) {
        int successStatus = vocidService.updateEntity(id, newEntity);
        return responseEntityUtil.getResponse(successStatus);
    }
    private static final Logger LOG = Logger.getLogger(VocidApi.class.getName());
}
