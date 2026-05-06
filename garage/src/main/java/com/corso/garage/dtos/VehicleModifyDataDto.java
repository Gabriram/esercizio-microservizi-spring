package com.corso.garage.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleModifyDataDto {
    private String vehicleType;
    private Long externalId;
}
