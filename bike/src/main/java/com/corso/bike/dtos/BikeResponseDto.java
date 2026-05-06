package com.corso.bike.dtos;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BikeResponseDto {

    @Id
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    @Min(50)
    private Integer engCc;

    @NotBlank
    private String type;

    @NotNull
    @Min(1900)
    private Integer year;

    @NotNull
    @Min(0)
    private Double price;

}
