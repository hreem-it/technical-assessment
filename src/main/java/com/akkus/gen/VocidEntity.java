/*
 * Copyright (C) 2019 Abdurrahman AKKUŞ <iletisim@algoritimbilisim.com>
 * Bu yazılımın tüm hakları Algoritim Bilişim'e aittir.
 */
package com.akkus.gen;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Abdurrahman AKKUŞ 
 */
@Data
@Entity
@Table(name="vocid")
public class VocidEntity implements ISuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id; 
    
    @Column(name="ssn")
    private Integer ssn;
    
    @Column(name="name")
    private String name;
    
    @Column(name="surname")
    private String surname;
    
    @Column(name="age")
    private int age;
    
    @Column(name="symptoms")
    private String symptoms;
    
    @Column(name="hospital")
    private String hospital;

}