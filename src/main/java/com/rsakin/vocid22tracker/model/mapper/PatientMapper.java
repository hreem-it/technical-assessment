package com.rsakin.vocid22tracker.model.mapper;

import com.rsakin.vocid22tracker.model.dto.PatientDTO;
import com.rsakin.vocid22tracker.model.entity.Patient;
import org.mapstruct.Mapper;

@Mapper
public interface PatientMapper {
    PatientDTO toDto(Patient entity);

    Patient toEntity(PatientDTO dto);
}
