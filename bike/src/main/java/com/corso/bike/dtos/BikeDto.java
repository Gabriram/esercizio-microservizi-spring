
package com.corso.bike.dtos;

import com.corso.bike.entity.Bike;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BikeDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String brand; // es. Ducati

    @NotBlank
    private String model; // es. Panigale V4

    @Min(50)
    private Integer eng_cc; // cilindrata in cc

    @NotBlank
    private String type; // sport, naked, enduro, scooter...

    @NotBlank
    private Integer year;

    @NotBlank
    private Double price;

    // Mapper: convert Bike entity to BikeDto
    public static BikeDto fromBike(Bike bike) {
        return BikeDto.builder()
                .brand(bike.getBrand())
                .model(bike.getModel())
                .eng_cc(bike.getEngCc())
                .type(bike.getType())
                .year(bike.getYear())
                .price(bike.getPrice())
                .build();
    }

}