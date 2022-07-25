package com.rsakin.vocid22tracker.model.entity;

import com.rsakin.vocid22tracker.model.converter.SymptomListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // we can also keep ssn as Id and unique, it ll be better to index this field if we are using generally ssn
    // while searching the patients
    @Column(name = "ssn")
    private String socialSecurityNumber;
    private String name;
    private String lastname;
    // We can manage genders via enum to make them const specific fields
    private String gender;
    private int age;
    // We dont need to keep this as oneToMany,
    // because there ll be not so many symptoms and not complicated that needs managing on table
    @Convert(converter = SymptomListConverter.class)
    private List<String> symptoms;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital admittedHospital;

}
