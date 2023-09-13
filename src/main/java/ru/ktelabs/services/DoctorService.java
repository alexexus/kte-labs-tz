package ru.ktelabs.services;

import ru.ktelabs.models.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor createDoctor(Doctor doctor);

    Doctor updateDoctor(long id, Doctor doctor);

    void deleteDoctor(long id);

    Doctor getDoctorById(long id);

    List<Doctor> getAllDoctors();
}
