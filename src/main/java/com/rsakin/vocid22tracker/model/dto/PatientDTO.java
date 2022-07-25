package com.rsakin.vocid22tracker.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class PatientDTO {
    @NotBlank
    private String socialSecurityNumber;
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    private String gender;
    private int age;
    private List<String> symptoms;
}
