package com.corso.bike;

import org.springframework.stereotype.Component;

import com.corso.bike.dtos.BikeModifyDTO;
import com.corso.bike.dtos.BikeResponseDTO;
import com.corso.bike.entity.Bike;

@Component
public class BikeMapper {

    public BikeResponseDTO toBikeResponseDto(Bike bike) {
        return BikeResponseDTO.builder()
                .id(bike.getId())
                .brand(bike.getBrand())
                .model(bike.getModel())
                .engCc(bike.getEngCc())
                .type(bike.getType())
                .year(bike.getYear())
                .price(bike.getPrice())
                .build();
    }

    public Bike toBikeEntity(BikeModifyDTO bikeModifyDTO) {
        return Bike.builder()
                .brand(bikeModifyDTO.getBrand())
                .model(bikeModifyDTO.getModel())
                .engCc(bikeModifyDTO.getEngCc())
                .type(bikeModifyDTO.getType())
                .year(bikeModifyDTO.getYear())
                .price(bikeModifyDTO.getPrice())
                .build();
    }

}
