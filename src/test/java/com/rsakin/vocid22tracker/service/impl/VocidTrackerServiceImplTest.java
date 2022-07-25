package com.rsakin.vocid22tracker.service.impl;

import com.rsakin.vocid22tracker.exception.NotFoundException;
import com.rsakin.vocid22tracker.model.dto.PatientDTO;
import com.rsakin.vocid22tracker.model.entity.Hospital;
import com.rsakin.vocid22tracker.model.entity.Patient;
import com.rsakin.vocid22tracker.model.mapper.PatientMapper;
import com.rsakin.vocid22tracker.repo.PatientRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VocidTrackerServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper PATIENT_MAPPER;

    @InjectMocks
    private VocidTrackerServiceImpl vocidTrackerService;

    @Test
    void getAllPatients() {
        // init expected
        List<Patient> sampleExpectedPatients = getSamplePatients();

        // stub - when
        when(patientRepository.findAll()).thenReturn(sampleExpectedPatients);

        // then test
        List<Patient> actualPatients = vocidTrackerService.getAllPatients();

        Assert.assertEquals(sampleExpectedPatients.size(), actualPatients.size());
        for (Patient expected : sampleExpectedPatients) {
            Optional<Patient> actual = actualPatients
                    .stream().filter(
                            airportCompany -> Objects.equals(airportCompany.getId(), expected.getId())
                    ).findFirst();
            // We can compare each fields here
            Assert.assertEquals(expected.getSocialSecurityNumber(), actual.get().getSocialSecurityNumber());
            Assert.assertEquals(expected.getName(), actual.get().getName());
        }

    }

    @Test
    void getPatientById_successfull() {
        // init expected
        Patient expPatient = getSamplePatients().get(0);
        Optional<Patient> optExpectedPatient = Optional.of(expPatient);

        // stub - when
        when(patientRepository.findById(any())).thenReturn(optExpectedPatient);

        // then
        Patient actualPassenger = vocidTrackerService.getPatientById(1L);

        // validate
        assertEquals(expPatient, actualPassenger);

    }

    @Test
    void getPatientById_notFound() {
        // stub - when
        when(patientRepository.findById(any())).thenReturn(Optional.empty());

        // then test
        assertThrows(NotFoundException.class,
                () -> {
                    Patient actualPatient = vocidTrackerService.getPatientById(1L);
                }
        );

    }

    @Test
    void addPatient() {
        // init
        Patient expectedPatient = getSamplePatients().get(0);
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setSocialSecurityNumber("12312312312312");

        // stub - when
        when(patientRepository.save(any())).thenReturn(expectedPatient);

        // then
        Patient patient = vocidTrackerService.addPatient(patientDTO);

        Assert.assertNotNull(patient);
    }

    @Test
    void updatePatient() {
        // almost the same with addPatient
        // init expected

        // stub - when

        // then test
    }

    @Test
    void deletePatient() {
        // given - precondition or setup
        Long patientId = 1L;
        Patient patient = getSamplePatients().get(0);

        when(patientRepository.findById(any())).thenReturn(Optional.of(patient));
        doNothing().when(patientRepository).deleteById(patientId);

        // when -  action or the behaviour that we are going test
        boolean isDeleted = vocidTrackerService.deletePatient(patientId);

        // then - verify the output
        Assert.assertTrue(isDeleted);
    }

    @Test
    void getAllPatientsByHospital() {
        // init expected
        List<Patient> samplePatients = getSamplePatients();
        Long hosp_id = 1L;
        List<Patient> expAllPatientsByHospital = samplePatients.stream()
                .filter(patient -> hosp_id.equals(patient.getAdmittedHospital().getId()))
                .collect(Collectors.toList());

        // stub - when
        when(patientRepository.findAll()).thenReturn(samplePatients);

        // then test
        List<Patient> actAllPatientsByHospital = vocidTrackerService.getAllPatientsByHospital(1L);
        Assert.assertEquals(expAllPatientsByHospital.size(), actAllPatientsByHospital.size());
    }

    public static List<Patient> getSamplePatients() {
        Hospital sampleHospital1 = new Hospital(1L, "Afyonkarahisar State Hospital", "Afyon", "Turkey", null);
        Hospital sampleHospital2 = new Hospital(2L, "Karolinska Universitetssjukhuset", "Solna", "Sweden", null);
        return Arrays.asList(
                new Patient(1L, "11223344551", "John", "Doe", "Male", 32,
                        Arrays.asList("Cough", "Fever or chills", "Sore throat", "Congestion or runny nose"), sampleHospital1),
                new Patient(2L, "11223344552", "Emily", "Zoe", "Female", 25,
                        Arrays.asList("Headache", "Fatigue"), sampleHospital1),
                new Patient(3L, "11223344553", "ZEmily", "Foe", "Male", 21,
                        Arrays.asList("Cough", "Fever or chills", "Sore throat", "Congestion or runny nose"), sampleHospital2),
                new Patient(4L, "11223344554", "PEmily", "Koe", "Female", 25,
                        Arrays.asList("Headache", "Fever or chills", "Sore throat"), sampleHospital1),
                new Patient(5L, "11223344555", "BEmily", "Moe", "Female", 35,
                        Arrays.asList("New loss of taste or smell", "Fever or chills", "Sore throat"), sampleHospital2),
                new Patient(6L, "11223344556", "FEmily", "Hoe", "Male", 75,
                        Arrays.asList("Diarrhea", "Fever or chills", "Congestion or  runny nose"), sampleHospital1),
                new Patient(7L, "11223344557", "CEmily", "Coe", "Female", 40,
                        Arrays.asList("Diarrhea", "Fever or chills", "Sore throat"), sampleHospital2),
                new Patient(8L, "11223344558", "KEmily", "Yoe", "Female", 18,
                        Arrays.asList("Cough", "Fever or chills", "Sore throat", "Congestion or runny nose"), sampleHospital2),
                new Patient(9L, "11223344559", "SEmily", "Toe", "Male", 15,
                        Arrays.asList("Headache"), sampleHospital2),
                new Patient(10L, "11223344550", "HEmily", "Moe", "Female", 63,
                        Arrays.asList("Fatigue"), sampleHospital2)
        );
    }
}