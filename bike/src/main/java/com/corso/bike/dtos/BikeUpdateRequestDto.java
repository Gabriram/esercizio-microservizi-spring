package com.corso.bike.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BikeUpdateRequestDto {
    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    @Min(50)
    private int engCc;

    @NotBlank
    private String type;

    @NotNull
    @Min(1900)
    private int year;

    @NotNull
    @Min(0)
    private double price;

}
