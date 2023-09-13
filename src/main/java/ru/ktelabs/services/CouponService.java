package ru.ktelabs.services;

import ru.ktelabs.models.Coupon;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponService {

    Coupon createCoupon(Coupon coupon);

    Coupon updateCoupon(long id, Coupon coupon);

    void deleteCoupon(long id);

    Coupon getCouponById(long id);

    List<Coupon> getAllCoupons();

    List<Coupon> getAllCouponsByPatientId(long id);

    List<Coupon> getAllCouponsByDoctorId(long id);

    List<Coupon> getAllCouponsByReceptionTimeIsBetween(LocalDateTime start, LocalDateTime end);
}
