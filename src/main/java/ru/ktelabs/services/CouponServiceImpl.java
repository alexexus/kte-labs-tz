package ru.ktelabs.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ktelabs.exception.ConflictException;
import ru.ktelabs.exception.NotFoundException;
import ru.ktelabs.models.Coupon;
import ru.ktelabs.models.Doctor;
import ru.ktelabs.models.Patient;
import ru.ktelabs.repositories.CouponRepository;
import ru.ktelabs.repositories.DoctorRepository;
import ru.ktelabs.repositories.PatientRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository repository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        Doctor doctor = doctorRepository.findById(coupon.getDoctor().getId())
                .orElseThrow(() -> new NotFoundException("Doctor not found"));
        Patient patient = patientRepository.findById(coupon.getPatient().getId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        coupon.setDoctor(doctor);
        coupon.setPatient(patient);
        if (repository.existsCouponsByReceptionStartTimeIsAfterAndReceptionEndTimeIsBefore(coupon.
                getReceptionStartTime().minusSeconds(1), coupon.getReceptionEndTime().plusSeconds(1))) {
            throw new ConflictException("This time is already taken");
        }
        if (coupon.getReceptionStartTime().isAfter(coupon.getReceptionEndTime())) {
            throw new ConflictException("The start of the reception cannot be after the end");
        }
        return repository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(long id, Coupon coupon) {
        Coupon oldCoupon = getCouponById(id);
        if (coupon.getDoctor() != null) {
            Doctor doctor = doctorRepository.findById(coupon.getDoctor().getId())
                    .orElseThrow(() -> new NotFoundException("Doctor not found"));
            oldCoupon.setDoctor(doctor);
        }
        if (coupon.getPatient() != null) {
            Patient patient = patientRepository.findById(coupon.getPatient().getId())
                    .orElseThrow(() -> new NotFoundException("Patient not found"));
            oldCoupon.setPatient(patient);
        }
        if (coupon.getReceptionStartTime() != null) {
            oldCoupon.setReceptionStartTime(coupon.getReceptionStartTime());
            if (repository.existsCouponsByReceptionStartTimeIsAfterAndReceptionEndTimeIsBefore(oldCoupon.
                    getReceptionStartTime().minusSeconds(1), coupon.getReceptionEndTime().plusSeconds(1))) {
                throw new ConflictException("This time is already taken");
            }
        }
        if (coupon.getReceptionEndTime() != null) {
            oldCoupon.setReceptionEndTime(coupon.getReceptionEndTime());
            if (repository.existsCouponsByReceptionStartTimeIsAfterAndReceptionEndTimeIsBefore(oldCoupon.
                    getReceptionStartTime().minusSeconds(1), coupon.getReceptionEndTime().plusSeconds(1))) {
                throw new ConflictException("This time is already taken");
            }
        }
        return repository.save(oldCoupon);
    }

    @Override
    public void deleteCoupon(long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Coupon not found");
        }
        repository.deleteById(id);
    }

    @Override
    public Coupon getCouponById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Coupon not found"));
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return repository.findAll();
    }

    @Override
    public List<Coupon> getAllCouponsByPatientId(long id) {
        if (!patientRepository.existsById(id)) {
            throw new NotFoundException("Patient not found");
        }
        return repository.findByPatientId(id);
    }

    @Override
    public List<Coupon> getAllCouponsByDoctorId(long id) {
        if (!doctorRepository.existsById(id)) {
            throw new NotFoundException("Doctor not found");
        }
        return repository.findByDoctorId(id);
    }

    @Override
    public List<Coupon> getAllCouponsByReceptionTimeIsBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByReceptionStartTimeIsAfterAndReceptionEndTimeIsBefore(start.minusSeconds(1),
                end.plusSeconds(1));
    }
}
