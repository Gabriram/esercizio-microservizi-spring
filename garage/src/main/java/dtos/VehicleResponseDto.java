package dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleResponseDto {
    private Long id;
    private String vehicleType;
    private Long externalId;
}
