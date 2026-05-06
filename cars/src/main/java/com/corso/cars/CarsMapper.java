package com.corso.cars;

import org.springframework.stereotype.Component;

import com.corso.cars.dtos.CarsModifyDataDTO;
import com.corso.cars.dtos.CarsResponseDto;
import com.corso.cars.entity.Cars;

@Component
public class CarsMapper {

    public CarsResponseDto toCarsResponseDto(Cars car) {
        return CarsResponseDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .fuelType(car.getFuelType())
                .doors(car.getDoors())
                .seats(car.getSeats())
                .year(car.getYear())
                .price(car.getPrice())
                .build();
    }

    public Cars toCarEntity(CarsModifyDataDTO dto) {
        return Cars.builder()
                .brand(dto.getBrand())
                .model(dto.getModel())
                .fuelType(dto.getFuelType())
                .doors(dto.getDoors())
                .seats(dto.getSeats())
                .year(dto.getYear())
                .price(dto.getPrice())
                .build();
    }

}
