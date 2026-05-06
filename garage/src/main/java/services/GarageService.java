package services;

import org.springframework.stereotype.Service;
import entities.Vehicle;
import repository.GarageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.client.HttpClientErrorException;
import dtos.GarageModifyDataDto;
import dtos.VehicleModifyDataDto;
import dtos.GarageResponseDto;
import dtos.VehicleResponseDto;
import entities.Garage;
import dtos.*;
import org.springframework.http.HttpStatus;
import repository.VehicleRepository;

@Service
public class GarageService {

    private final VehicleRepository vehicleRepository;
    private final VehicleValidator vehicleValidator;
    private final GarageRepository garageRepository;

    @Value("${service.bikes.url}")
    private String bikesUrl;

    @Value("${service.cars.url}")
    private String carsUrl;

    GarageService(VehicleValidator vehicleValidator, GarageRepository garageRepository, VehicleRepository vehicleRepository) {
        this.vehicleValidator = vehicleValidator;
        this.garageRepository = garageRepository;
        this.vehicleRepository = vehicleRepository;
    }

 public Vehicle addVehicle(Long garageId, Vehicle v) {
    if (!vehicleValidator.exists(v.getVehicleType(), v.getExternalId())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Vehicle " + v.getVehicleType() + ":" + v.getExternalId() + " does not exist");
    }
    Garage garage = garageRepository.findById(garageId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    v.setGarage(garage);
        garage.addVehicle(v);
    garageRepository.save(garage);
    return v;
}   
    }
}
