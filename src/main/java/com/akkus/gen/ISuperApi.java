/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akkus.gen;

import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author dijital
 * @param <T>
 */
// ISuperEntity interface de olsa generic constraint kavramı için extends ile kullanılır!!!
public interface ISuperApi<T extends ISuperEntity> {

    /**
     *
     * @return
     */
    public List<T> getAll();

    /**
     *
     * @param newEntity implements ISuperEntity
     * @return 
     */
    public ResponseEntity<List<T>> setNew(T newEntity);

    /**
     *
     * @param id
     * @return
     */
    public ResponseEntity delete(int id);

    /**
     *
     * @param id
     * @param newEntity
     * @return
     */
    public ResponseEntity update(int id, T newEntity);
}
