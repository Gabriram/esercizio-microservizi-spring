package com.corso.garage.dtos;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class GarageResponseDto {

    private Long id;

    private String ownerName;

    private String address;

    private List<VehicleModifyDataDto> vehicles;

}
