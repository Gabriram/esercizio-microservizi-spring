package com.corso.bike.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BikeModifyDTO {

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
