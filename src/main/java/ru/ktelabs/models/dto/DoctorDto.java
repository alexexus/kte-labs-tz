package ru.ktelabs.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {

    @NotBlank
    @Length(min = 1, max = 20)
    private String firstName;

    @NotBlank
    @Length(min = 1, max = 20)
    private String lastName;
}
