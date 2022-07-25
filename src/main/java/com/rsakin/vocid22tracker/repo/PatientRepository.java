package com.rsakin.vocid22tracker.repo;

import com.rsakin.vocid22tracker.model.entity.Hospital;
import com.rsakin.vocid22tracker.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> getAllByAdmittedHospital(Hospital hospital);

}
