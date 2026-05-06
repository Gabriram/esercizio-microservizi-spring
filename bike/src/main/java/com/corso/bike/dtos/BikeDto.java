package com.corso.bike.dtos;

import com.corso.bike.entity.Bike;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BikeDto {

    private Long id;

    @NotBlank
    private String brand; // es. Ducati

    @NotBlank
    private String model; // es. Panigale V4

    @Min(50)
    @JsonProperty("eng_cc")
    private Integer engCc; // cilindrata in cc

    @NotBlank
    private String type; // sport, naked, enduro, scooter...

    @NotNull
    @Min(1900)
    private Integer year;

    @NotNull
    private Double price;

    // Mapper: convert Bike entity to BikeDto
    public static BikeDto fromBike(Bike bike) {
        return BikeDto.builder()
                .brand(bike.getBrand())
                .model(bike.getModel())
                .engCc(bike.getEngCc())
                .type(bike.getType())
                .year(bike.getYear())
                .price(bike.getPrice())
                .build();
    }

}