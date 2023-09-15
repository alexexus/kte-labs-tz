package ru.ktelabs.services.impls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ktelabs.exceptions.NotFoundException;
import ru.ktelabs.models.Patient;
import ru.ktelabs.repositories.PatientRepository;
import ru.ktelabs.services.PatientService;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    @Override
    public Patient createPatient(Patient patient) {
        return repository.save(patient);
    }

    @Override
    public Patient updatePatient(long id, Patient patient) {
        Patient oldPatient = getPatientById(id);
        if (patient.getFirstName() != null) {
            oldPatient.setFirstName(patient.getFirstName());
        }
        if (patient.getLastName() != null) {
            oldPatient.setLastName(patient.getLastName());
        }
        if (patient.getDateOfBirth() != null) {
            oldPatient.setDateOfBirth(patient.getDateOfBirth());
        }
        return repository.save(oldPatient);
    }

    @Override
    public void deletePatient(long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Patient not found");
        }
        repository.deleteById(id);
    }

    @Override
    public Patient getPatientById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient not found"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }
}
