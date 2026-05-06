package services;

import org.springframework.stereotype.Service;
import entities.Vehicle;
import repository.GarageRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.HttpClientErrorException;
import dtos.GarageModifyDataDto;
import dtos.VehicleModifyDataDto;
import dtos.GarageResponseDto;
import dtos.VehicleResponseDto;
import entities.Garage;
import entities.Vehicle;
import dtos.*;

@Service
public class GarageService {

    private final VehicleValidator vehicleValidator;

    @Value("${service.bikes.url}")
    private String bikesUrl;

    @Value("${service.cars.url}")
    private String carsUrl;

    GarageService(VehicleValidator vehicleValidator) {
        this.vehicleValidator = vehicleValidator;
    }

    public Vehicle addVehicle(Long garageId, Vehicle v) {
        if (!vehicleValidator.exists(v.getVehicleType(), v.getExternalId())) {
            throw new IllegalArgumentException(HttpStatus.BAD_REQUEST,
                    "Vehicle with type " + v.getVehicleType() + " and id "
                            + v.getExternalId() + " does not exist in the corresponding service.");
        }
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new IllegalArgumentException(HttpStatus.NOT_FOUND));

    }

}
