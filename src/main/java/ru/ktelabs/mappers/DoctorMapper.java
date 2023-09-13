package ru.ktelabs.mappers;

import org.springframework.stereotype.Component;
import ru.ktelabs.models.Doctor;
import ru.ktelabs.models.DoctorDto;

@Component
public class DoctorMapper {

    public Doctor toDoctor(DoctorDto doctorDto) {
        return Doctor.builder()
                .firstName(doctorDto.getFirstName())
                .lastName(doctorDto.getLastName())
                .build();
    }
}
