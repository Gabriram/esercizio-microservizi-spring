package com.corso.bike;

import org.springframework.stereotype.Component;

import com.corso.bike.dtos.BikeCreateDto;
import com.corso.bike.dtos.BikeResponseDto;
import com.corso.bike.dtos.BikeUpdateRequestDto;
import com.corso.bike.entity.Bike;

@Component
public class BikeMapper {

    public BikeResponseDto toResponseDto(Bike bike) {
        return BikeResponseDto.builder()
                .id(bike.getId())
                .brand(bike.getBrand())
                .model(bike.getModel())
                .engCc(bike.getEngCc())
                .type(bike.getType())
                .year(bike.getYear())
                .price(bike.getPrice())
                .build();
    }

    public Bike toBikeEntity(BikeCreateDto bikeCreateDto) {
        return Bike.builder()
                .brand(bikeCreateDto.getBrand())
                .model(bikeCreateDto.getModel())
                .engCc(bikeCreateDto.getEngCc())
                .type(bikeCreateDto.getType())
                .year(bikeCreateDto.getYear())
                .price(bikeCreateDto.getPrice())
                .build();
    }

    public Bike toBikeEntity(BikeUpdateRequestDto bikeUpdateRequestDto) {
        return Bike.builder()
                .brand(bikeUpdateRequestDto.getBrand())
                .model(bikeUpdateRequestDto.getModel())
                .engCc(bikeUpdateRequestDto.getEngCc())
                .type(bikeUpdateRequestDto.getType())
                .year(bikeUpdateRequestDto.getYear())
                .price(bikeUpdateRequestDto.getPrice())
                .build();
    }

}
