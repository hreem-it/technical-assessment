package com.rsakin.vocid22tracker;

import com.rsakin.vocid22tracker.model.entity.Hospital;
import com.rsakin.vocid22tracker.model.entity.Patient;
import com.rsakin.vocid22tracker.repo.HospitalRepository;
import com.rsakin.vocid22tracker.repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// Creating sample Dummy data for "local" env
@Component
@RequiredArgsConstructor
@Profile("local")
public class DataLoader implements ApplicationRunner {

    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;

    public void run(ApplicationArguments args) {
        hospitalRepository.saveAll(
                Arrays.asList(
                        new Hospital(1L, "Afyonkarahisar State Hospital", "Afyon", "Turkey", null),
                        new Hospital(2L, "Tuzla State Hospital", "Istanbul", "Turkey", null),
                        new Hospital(3L, "Istituto Clinico Humanitas", "Milan", "Italy", null),
                        new Hospital(4L, "Acibadem Private Hospital", "Istanbul", "Turkey", null),
                        new Hospital(5L, "Policlinico Universitario Gemelli", "Rome", "Italy", null),
                        new Hospital(6L, "Karolinska Universitetssjukhuset", "Solna", "Sweden", null)
                )
        );

        patientRepository.saveAll(
                Arrays.asList(
                        new Patient(1L, "11223344551", "John", "Doe", "Male", 32,
                                Arrays.asList("Cough", "Fever or chills", "Sore throat", "Congestion or runny nose"), hospitalRepository.findById(1L).get()),
                        new Patient(2L, "11223344552", "Emily", "Zoe", "Female", 25,
                                Arrays.asList("Headache", "Fatigue"), hospitalRepository.findById(2L).get()),
                        new Patient(3L, "11223344553", "ZEmily", "Foe", "Male", 21,
                                Arrays.asList("Cough", "Fever or chills", "Sore throat", "Congestion or runny nose"), hospitalRepository.findById(2L).get()),
                        new Patient(4L, "11223344554", "PEmily", "Koe", "Female", 25,
                                Arrays.asList("Headache", "Fever or chills", "Sore throat"), hospitalRepository.findById(3L).get()),
                        new Patient(5L, "11223344555", "BEmily", "Moe", "Female", 35,
                                Arrays.asList("New loss of taste or smell", "Fever or chills", "Sore throat"), hospitalRepository.findById(3L).get()),
                        new Patient(6L, "11223344556", "FEmily", "Hoe", "Male", 75,
                                Arrays.asList("Diarrhea", "Fever or chills", "Congestion or  runny nose"), hospitalRepository.findById(4L).get()),
                        new Patient(7L, "11223344557", "CEmily", "Coe", "Female", 40,
                                Arrays.asList("Diarrhea", "Fever or chills", "Sore throat"), hospitalRepository.findById(5L).get()),
                        new Patient(8L, "11223344558", "KEmily", "Yoe", "Female", 18,
                                Arrays.asList("Cough", "Fever or chills", "Sore throat", "Congestion or runny nose"), hospitalRepository.findById(5L).get()),
                        new Patient(9L, "11223344559", "SEmily", "Toe", "Male", 15,
                                Arrays.asList("Headache"), hospitalRepository.findById(5L).get()),
                        new Patient(10L, "11223344550", "HEmily", "Moe", "Female", 63,
                                Arrays.asList("Fatigue"), hospitalRepository.findById(6L).get())
                )
        );
    }
}
