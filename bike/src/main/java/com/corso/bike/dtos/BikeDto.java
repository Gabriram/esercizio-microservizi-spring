
package com.corso.bike.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BikeDto {

    private String brand; // es. Ducati

    private String model; // es. Panigale V4

    private Integer engCc; // cilindrata in cc

    private String type; // sport, naked, enduro, scooter...

    private Integer year;

    private Double price;

    // Mapper: convert Bike entity to BikeDto
    public static BikeDto fromBike(com.corso.bike.entity.Bike bike) {
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