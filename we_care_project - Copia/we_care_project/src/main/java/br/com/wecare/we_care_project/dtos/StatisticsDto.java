package br.com.wecare.we_care_project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatisticsDto {

    @NotBlank
    private String city;

    @NotNull
    private float pib;

    @NotNull
    private float workingForce;

    @NotNull
    private float educationLevel;

    @NotNull
    private float poverty;

    @NotNull
    private float salary;

    @NotNull
    private float povertyPercentage;
}
