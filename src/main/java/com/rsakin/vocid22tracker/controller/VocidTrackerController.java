package com.rsakin.vocid22tracker.controller;

import com.rsakin.vocid22tracker.model.dto.PatientDTO;
import com.rsakin.vocid22tracker.model.entity.Patient;
import com.rsakin.vocid22tracker.service.VocidTrackerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vocid22")
public class VocidTrackerController {

    private final VocidTrackerService vocidTrackerService;

    @ApiOperation("Get all infected Patients")
    @GetMapping(value = "/patient/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> allPatients = vocidTrackerService.getAllPatients();
        return new ResponseEntity<>(allPatients, HttpStatus.OK);
    }

    @ApiOperation("Get any already defined patient by id")
    @GetMapping(value = "/patient/{patient_id}")
    public ResponseEntity<Patient> getPatient(
            @PathVariable @Min(1) final Long patient_id
    ) {
        Patient patient = vocidTrackerService.getPatientById(patient_id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @ApiOperation("Create a patient")
    @PostMapping(value = "/patient/create")
    public ResponseEntity<Patient> savePatient(
            @Valid @RequestBody final PatientDTO patientDTO
    ) {
        Patient savedPatient = vocidTrackerService.addPatient(patientDTO);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @ApiOperation("Update any already defined patient by id")
    @PutMapping(value = "/patient/update/{patient_id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable @Min(1) final Long patient_id,
            @Valid @RequestBody final PatientDTO patientDTO
    ) {
        Patient respPatient = vocidTrackerService.updatePatient(patient_id, patientDTO);
        return new ResponseEntity<>(respPatient, HttpStatus.OK);
    }

    @ApiOperation("Transport any already defined patient by id to another hospital")
    @PutMapping(value = "/patient/{patient_id}/transport/{hosp_id}")
    public ResponseEntity<Patient> transportPatient(
            @PathVariable @Min(1) final Long patient_id,
            @PathVariable @Min(1) final Long hosp_id
    ) {
        Patient transportedPatient = vocidTrackerService.transportPatient(patient_id, hosp_id);
        return new ResponseEntity<>(transportedPatient, HttpStatus.OK);
    }

    @ApiOperation("Delete any already defined patient by id")
    @DeleteMapping(value = "/patient/{patient_id}")
    public ResponseEntity<Boolean> deletePatient(
            @PathVariable @Min(1) final Long patient_id
    ) {
        boolean isDeleted = vocidTrackerService.deletePatient(patient_id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @ApiOperation("Get all Patients by Hospital")
    @GetMapping(value = "/hospital/{hosp_id}/patients")
    public ResponseEntity<List<Patient>> getAllPatientsByHospital(
            @PathVariable @Min(1) final Long hosp_id
    ) {
        List<Patient> allPatientsByHospital = vocidTrackerService.getAllPatientsByHospital(hosp_id);
        return ResponseEntity.status(HttpStatus.OK).body(allPatientsByHospital);
    }

    @ApiOperation("Get all Patients by related Symptoms")
    @PostMapping(value = "/patients-with-symptoms")
    public ResponseEntity<List<Patient>> getAllPatientsBySymptoms(
            @RequestBody final List<String> symptoms
    ) {
        List<Patient> allPatientsBySymptoms = vocidTrackerService.getAllPatientsBySymptoms(symptoms);
        return ResponseEntity.ok().body(allPatientsBySymptoms);
    }

}

