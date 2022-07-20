/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akkus.gen.util;

import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author dijital
 */
@Component
public class ResponseEntityUtil {

    /**
     *
     * @param basarim
     * @return
     */
    public ResponseEntity getResponse(int basarim) {
        if (basarim > 0) {
            return new ResponseEntity(basarim, OK);
        } else {
            return new ResponseEntity(INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     *
     * @param id
     * @param basarim
     * @return
     */
    public ResponseEntity getResponse(int id, int basarim) {
        if (basarim > 0) {
            return new ResponseEntity(id, OK);
        } else {
            return new ResponseEntity(INTERNAL_SERVER_ERROR);
        }
    }
    private static final Logger LOG = Logger.getLogger(ResponseEntityUtil.class.getName());
}
