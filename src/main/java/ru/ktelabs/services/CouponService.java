package ru.ktelabs.services;

import ru.ktelabs.models.Coupon;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponService {

    void deleteCoupon(long id);

    Coupon getCouponById(long id);

    List<Coupon> getAllCoupons();

    List<Coupon> getAllCouponsByPatientId(long id);

    List<Coupon> getAllFreeCouponsByDoctorId(long id);

    List<Coupon> getAllCouponsByReceptionTimeIsBetween(LocalDateTime start, LocalDateTime end);

    void createSchedule(long doctorId, LocalDateTime date);

    Coupon takeCouponByPatient(long patientId, long couponId);
}
