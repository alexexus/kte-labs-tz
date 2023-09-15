package ru.ktelabs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.mappers.DoctorMapper;
import ru.ktelabs.models.Doctor;
import ru.ktelabs.models.dtos.DoctorDto;
import ru.ktelabs.services.DoctorService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/doctors")
@Api
public class DoctorController {

    private final DoctorService service;
    private final DoctorMapper mapper;

    @PostMapping
    @ApiOperation("Добавление нового доктора")
    public Doctor createDoctor(@RequestBody DoctorDto doctorDto) {
        return service.createDoctor(mapper.toDoctor(doctorDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление существующего доктора по id")
    public void deleteDoctor(@PathVariable long id) {
        service.deleteDoctor(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Обновление существующего доктора по id")
    public Doctor updateDoctor(@PathVariable long id, @RequestBody DoctorDto doctorDto) {
        return service.updateDoctor(id, mapper.toDoctor(doctorDto));
    }

    @GetMapping("/{id}")
    @ApiOperation("Получение доктора по id")
    public Doctor getDoctorById(@PathVariable long id) {
        return service.getDoctorById(id);
    }

    @GetMapping
    @ApiOperation("Получение всех докторов")
    public List<Doctor> getAllDoctors() {
        return service.getAllDoctors();
    }
}
