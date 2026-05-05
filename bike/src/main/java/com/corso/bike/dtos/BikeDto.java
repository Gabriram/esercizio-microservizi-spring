
package com.corso.bike.dtos;

import com.corso.bike.entity.Bike;

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

    private String brand; // es. Ducati

    private String model; // es. Panigale V4

    private Integer engCc; // cilindrata in cc

    private String type; // sport, naked, enduro, scooter...

    private Integer year;

    private Double price;

    // Mapper: convert Bike entity to BikeDto
    public static BikeDto fromBike(Bike bike) {
        return BikeDto.builder()
                .id(bike.getId())
                .brand(bike.getBrand())
                .model(bike.getModel())
                .engCc(bike.getEngCc())
                .type(bike.getType())
                .year(bike.getYear())
                .price(bike.getPrice())
                .build();
    }

}