package ru.ktelabs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ktelabs.models.Coupon;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    boolean existsCouponsByReceptionStartTimeIsAfterAndReceptionEndTimeIsBefore(LocalDateTime start, LocalDateTime end);

    List<Coupon> findByPatientId(long patientId);

    List<Coupon> findByDoctorId(long doctorId);

    List<Coupon> findByReceptionStartTimeIsAfterAndReceptionEndTimeIsBefore(LocalDateTime start, LocalDateTime end);
}
