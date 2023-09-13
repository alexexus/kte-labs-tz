package ru.ktelabs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ktelabs.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
