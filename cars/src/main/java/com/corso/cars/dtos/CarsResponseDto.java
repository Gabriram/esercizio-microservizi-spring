package com.corso.cars.dtos;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class CarsResponseDto {

    @Id
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String fuelType;

    @Min(2)
    private Integer doors;

    @Min(2)
    private Integer seats;

    @Min(1900)
    private Integer year;

    @Min(0)
    private Double price;
}
