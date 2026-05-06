
package com.corso.bike.dtos;

import com.corso.bike.entity.Bike;

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
    private Integer eng_cc; // cilindrata in cc

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
                .id(bike.getId())
                .brand(bike.getBrand())
                .model(bike.getModel())
                .eng_cc(bike.getEngCc())
                .type(bike.getType())
                .year(bike.getYear())
                .price(bike.getPrice())
                .build();
    }

}