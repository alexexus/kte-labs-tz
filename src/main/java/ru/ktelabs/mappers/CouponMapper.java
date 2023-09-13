package ru.ktelabs.mappers;

import org.springframework.stereotype.Component;
import ru.ktelabs.models.Coupon;
import ru.ktelabs.models.CouponDto;
import ru.ktelabs.models.Doctor;
import ru.ktelabs.models.Patient;

@Component
public class CouponMapper {

    public Coupon toCoupon(CouponDto couponDto) {
        return Coupon.builder()
                .doctor(Doctor.builder().id(couponDto.getDoctorId()).build())
                .patient(Patient.builder().id(couponDto.getPatientId()).build())
                .receptionStartTime(couponDto.getReceptionStartTime())
                .receptionEndTime(couponDto.getReceptionEndTime())
                .build();
    }
}
