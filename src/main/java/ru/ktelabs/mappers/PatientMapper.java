package ru.ktelabs.mappers;

import org.springframework.stereotype.Component;
import ru.ktelabs.models.Patient;
import ru.ktelabs.models.dto.PatientDto;

@Component
public class PatientMapper {

    public Patient toPatient(PatientDto patientDto) {
        return Patient.builder()
                .firstName(patientDto.getFirstName())
                .lastName(patientDto.getLastName())
                .dateOfBirth(patientDto.getDateOfBirth())
                .build();
    }
}
