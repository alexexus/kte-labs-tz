package ru.ktelabs.services.impls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ktelabs.exceptions.ConflictException;
import ru.ktelabs.exceptions.NotFoundException;
import ru.ktelabs.models.Coupon;
import ru.ktelabs.models.Patient;
import ru.ktelabs.repositories.CouponRepository;
import ru.ktelabs.repositories.DoctorRepository;
import ru.ktelabs.repositories.PatientRepository;
import ru.ktelabs.services.CouponService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository repository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

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
    public List<Coupon> getAllFreeCouponsByDoctorId(long id) {
        if (!doctorRepository.existsById(id)) {
            throw new NotFoundException("Doctor not found");
        }
        return repository.findByDoctorIdAndPatientIsNull(id);
    }

    @Override
    public List<Coupon> getAllCouponsByReceptionTimeIsBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByReceptionStartTimeIsAfterAndReceptionEndTimeIsBefore(start.minusSeconds(1),
                end.plusSeconds(1));
    }

    @Override
    public void createSchedule(long doctorId, LocalDateTime date) {
        if (date.getHour() < 8 || date.getHour() > 17) {
            throw new ConflictException("Working hours from 8:00 to 17:00");
        }
        List<Coupon> coupons = new ArrayList<>();
        while (date.getHour() < 18) {
            coupons.add(Coupon.builder()
                    .doctor(doctorRepository.findById(doctorId)
                            .orElseThrow(() -> new NotFoundException("Doctor not found")))
                    .patient(null)
                    .receptionStartTime(date)
                    .receptionEndTime(date.plusMinutes(59))
                    .build());
            date = date.plusHours(1);
        }
        repository.saveAll(coupons);
    }

    @Override
    public Coupon takeCouponByPatient(long patientId, long couponId) {
        Coupon coupon = repository.findById(couponId)
                .orElseThrow(() -> new NotFoundException("Coupon not found"));
        if (coupon.getPatient() != null) {
            throw new ConflictException("Coupon already taken");
        }
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        coupon.setPatient(patient);
        return repository.save(coupon);
    }
}
