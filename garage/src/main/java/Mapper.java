import org.springframework.stereotype.Component;
import dtos.*;
import entities.*;

@Component
public class Mapper {

    public Garage toGarageEntity(GarageModifyDataDto dto) {
        return Garage.builder()
                .ownerName(dto.getOwnerName())
                .address(dto.getAddress())
                .build();
    }

    public Vehicle toVehicleEntity(VehicleModifyDataDto dto) {
        return Vehicle.builder()
                .vehicleType(dto.getVehicleType())
                .externalId(dto.getExternalId())
                .build();
    }

    public VehicleResponseDto toVehicleResponseDto(Vehicle vehicle) {
        return VehicleResponseDto.builder()
                .id(vehicle.getId())
                .vehicleType(vehicle.getVehicleType())
                .externalId(vehicle.getExternalId())
                .build();
    }

    public GarageResponseDto toGarageResponseDto(Garage garage) {
        return GarageResponseDto.builder()
                .id(garage.getId())
                .ownerName(garage.getOwnerName())
                .address(garage.getAddress())
                .build();
    }

}
