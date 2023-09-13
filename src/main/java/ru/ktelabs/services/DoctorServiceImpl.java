package ru.ktelabs.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ktelabs.exception.NotFoundException;
import ru.ktelabs.models.Doctor;
import ru.ktelabs.repositories.DoctorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return repository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(long id, Doctor doctor) {
        Doctor oldDoctor = getDoctorById(id);
        if (doctor.getFirstName() != null) {
            oldDoctor.setFirstName(doctor.getFirstName());
        }
        if (doctor.getLastName() != null) {
            oldDoctor.setLastName(doctor.getLastName());
        }
        return repository.save(oldDoctor);
    }

    @Override
    public void deleteDoctor(long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Doctor not found");
        }
        repository.deleteById(id);
    }

    @Override
    public Doctor getDoctorById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return repository.findAll();
    }
}
