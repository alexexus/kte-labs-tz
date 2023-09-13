package ru.ktelabs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.mappers.CouponMapper;
import ru.ktelabs.models.Coupon;
import ru.ktelabs.models.CouponDto;
import ru.ktelabs.services.CouponService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/coupons")
@Api
public class CouponController {

    private final CouponService service;
    private final CouponMapper mapper;

    @PostMapping
    @ApiOperation("Добавление нового талона")
    public Coupon createCoupon(@RequestBody CouponDto couponDto) {
        return service.createCoupon(mapper.toCoupon(couponDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление существующего талона по id")
    public void deleteCoupon(@PathVariable long id) {
        service.deleteCoupon(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Обновление существующего талона по id")
    public Coupon updateCoupon(@PathVariable long id, @RequestBody CouponDto couponDto) {
        return service.updateCoupon(id, mapper.toCoupon(couponDto));
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
    @ApiOperation("Получение всех талонов у доктора по его id")
    public List<Coupon> getAllCouponsByDoctorId(@PathVariable long id) {
        return service.getAllCouponsByDoctorId(id);
    }

    @GetMapping("/reception_time_is_between")
    @ApiOperation("Получение всех талонов по времени между start и end")
    public List<Coupon> getAllCouponsByTimeBetween(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime start,
                                                   @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime end) {
        return service.getAllCouponsByReceptionTimeIsBetween(start, end);
    }
}
