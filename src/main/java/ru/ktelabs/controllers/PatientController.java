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
import ru.ktelabs.mappers.PatientMapper;
import ru.ktelabs.models.Patient;
import ru.ktelabs.models.PatientDto;
import ru.ktelabs.services.PatientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patients")
@Api
public class PatientController {

    private final PatientService service;
    private final PatientMapper mapper;

    @PostMapping
    @ApiOperation("Добавление нового пациента")
    public Patient createPatient(@RequestBody PatientDto patientDto) {
        return service.createPatient(mapper.toPatient(patientDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление существующего пациента по id")
    public void deletePatient(@PathVariable long id) {
        service.deletePatient(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Обновление существующего пациента по id")
    public Patient updatePatient(@PathVariable long id, @RequestBody PatientDto patientDto) {
        return service.updatePatient(id, mapper.toPatient(patientDto));
    }

    @GetMapping("/{id}")
    @ApiOperation("Получение пациента по id")
    public Patient getPatientById(@PathVariable long id) {
        return service.getPatientById(id);
    }

    @GetMapping
    @ApiOperation("Получение всех пациентов")
    public List<Patient> getAllPatients() {
        return service.getAllPatients();
    }
}
