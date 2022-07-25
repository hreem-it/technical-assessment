package com.rsakin.vocid22tracker.service;

import com.rsakin.vocid22tracker.model.dto.PatientDTO;
import com.rsakin.vocid22tracker.model.entity.Patient;

import java.util.List;

public interface VocidTrackerService {

    List<Patient> getAllPatients();

    Patient getPatientById(final Long patient_id);

    Patient addPatient(final PatientDTO PatientDTO);

    Patient updatePatient(final Long patient_id, final PatientDTO patientDTO);

    boolean deletePatient(final Long patient_id);

    List<Patient> getAllPatientsByHospital(final Long hosp_id);

    List<Patient> getAllPatientsBySymptoms(final List<String> symptoms);

    Patient transportPatient(final Long patient_id, final Long hosp_id);
}
