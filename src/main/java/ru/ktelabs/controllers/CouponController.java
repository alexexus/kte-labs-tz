package ru.ktelabs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.models.Coupon;
import ru.ktelabs.services.CouponService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/coupons")
@Api
public class CouponController {

    private final CouponService service;

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление существующего талона по id")
    public void deleteCoupon(@PathVariable long id) {
        service.deleteCoupon(id);
    }

    @GetMapping("/{id}")
    @ApiOperation("Получение талона по id")
    public Coupon getCouponById(@PathVariable long id) {
        return service.getCouponById(id);
    }

    @GetMapping
    @ApiOperation("Получение всех талонов")
    public List<Coupon> getAllCoupons() {
        return service.getAllCoupons();
    }

    @GetMapping("/patients/{id}")
    @ApiOperation("Получение всех талонов занятых одним пациентом по его id")
    public List<Coupon> getAllCouponsByPatientId(@PathVariable long id) {
        return service.getAllCouponsByPatientId(id);
    }

    @GetMapping("/doctors/{id}")
    @ApiOperation("Получение всех свободных талонов у доктора по его id")
    public List<Coupon> getAllCouponsByDoctorId(@PathVariable long id) {
        return service.getAllFreeCouponsByDoctorId(id);
    }

    @GetMapping("/reception_time_is_between")
    @ApiOperation("Получение всех талонов по времени между start и end")
    public List<Coupon> getAllCouponsByTimeBetween(@RequestParam
                                                   @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime start,
                                                   @RequestParam
                                                   @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime end) {
        return service.getAllCouponsByReceptionTimeIsBetween(start, end);
    }

    @PostMapping("/schedule/{doctorId}")
    @ApiOperation("Создание расписания на переданный день")
    public void createSchedule(@PathVariable long doctorId,
                               @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime start) {
        service.createSchedule(doctorId, start);
    }

    @PostMapping("/{patientId}/{couponId}")
    @ApiOperation("Бронирование купона пациентом")
    public Coupon takeCouponByPatient(@PathVariable long patientId, @PathVariable long couponId) {
        return service.takeCouponByPatient(patientId, couponId);
    }

}
