package com.rsakin.vocid22tracker.service.impl;

import com.rsakin.vocid22tracker.exception.NotFoundException;
import com.rsakin.vocid22tracker.model.dto.PatientDTO;
import com.rsakin.vocid22tracker.model.entity.Hospital;
import com.rsakin.vocid22tracker.model.entity.Patient;
import com.rsakin.vocid22tracker.model.mapper.PatientMapper;
import com.rsakin.vocid22tracker.repo.PatientRepository;
import com.rsakin.vocid22tracker.service.VocidTrackerService;
import com.rsakin.vocid22tracker.util.CustomModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VocidTrackerServiceImpl implements VocidTrackerService {

    private final PatientRepository patientRepository;
    private static final PatientMapper PATIENT_MAPPER = Mappers.getMapper(PatientMapper.class);

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(final Long patient_id) {
        Optional<Patient> byId = patientRepository.findById(patient_id);
        return byId.orElseThrow(() -> {
            log.error("Patient entity not found with id : " + patient_id);
            return new NotFoundException("Patient by id : " + patient_id);
        });
    }

    @Override
    public Patient addPatient(final PatientDTO patientDTO) {
        Patient patient = PATIENT_MAPPER.toEntity(patientDTO);
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(final Long patient_id, final PatientDTO patientDTO) {
        Patient patientById = getPatientById(patient_id);
        Patient patient = PATIENT_MAPPER.toEntity(patientDTO);
        CustomModelMapper.myCopyProperties(patient, patientById);
        return patientRepository.save(patientById);
    }

    @Override
    public boolean deletePatient(final Long patient_id) {
        getPatientById(patient_id);
        patientRepository.deleteById(patient_id);
        return true;
    }

    @Override
    public List<Patient> getAllPatientsByHospital(final Long hosp_id) {
        // normally we need to have hospital side repo, service etc.
        // and need to use hospitalService here to check whether there is a hosp with the id or not
        // but for this repo we just have patientRepo

        // We can get hospital by Id via its own service and then just
        // Hospital hosp = hospitalService.getById(hosp_id);
        // List<Patient> allPatientsByHospital = patientRepository.getAllByAdmittedHospital(hosp);
        // return it :)

        // But we have no hospital side, so we can get all them from patient side
        List<Patient> allPatients = getAllPatients();
        // Also, we can check whether there is a hospital with the id or not
        checkHospital(hosp_id, allPatients);
        return allPatients.stream().filter(patient -> hosp_id.equals(patient.getAdmittedHospital().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> getAllPatientsBySymptoms(final List<String> symptoms) {
        List<Patient> allPatients = getAllPatients();
        Set<Patient> bySymptoms = new java.util.HashSet<>(Set.of());
        allPatients.forEach(patient -> patient.getSymptoms().forEach(s -> {
            if (symptoms.contains(s)) {
                bySymptoms.add(patient);
            }
        }));
        return new ArrayList<>(bySymptoms);
    }

    @Override
    public Patient transportPatient(final Long patient_id, final Long hosp_id) {
        Patient patientById = getPatientById(patient_id);
        // Normally, as I already mentioned above we can do this on hosp_service by id
        final var relatedHospital = checkHospital(hosp_id, getAllPatients());
        patientById.setAdmittedHospital(relatedHospital);
        return patientRepository.save(patientById);
    }

    private Hospital checkHospital(final Long hosp_id, List<Patient> allPatients) {
        // we can also use "var" keyword from java10 for local variables
        // also we can use it on lambda ops thanks to java11
        final var allHospitals = allPatients.stream()
                .map(Patient::getAdmittedHospital)
                .collect(Collectors.toList());
        final var relatedHospital =
                allHospitals.stream().filter(hospital -> hosp_id.equals(hospital.getId())).findFirst();
        return relatedHospital.orElseThrow(() -> new NotFoundException("Hospital by id : " + hosp_id));
    }

}
