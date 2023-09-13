package ru.ktelabs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ktelabs.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
