package com.corso.garage.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.corso.garage.GarageMapper;
import com.corso.garage.dtos.GarageResponseDto;
import com.corso.garage.dtos.GarageModifyDataDto;
import com.corso.garage.dtos.VehicleModifyDataDto;
import com.corso.garage.dtos.VehicleResponseDto;
import com.corso.garage.entities.Garage;
import com.corso.garage.entities.Vehicle;
import com.corso.garage.repository.GarageRepository;
import com.corso.garage.repository.VehicleRepository;

@Service
@RequiredArgsConstructor
public class GarageService {

    private final VehicleValidator vehicleValidator;
    private final GarageRepository garageRepository;
    private final GarageMapper garageMapper;

    @Value("${service.bikes.url}")
    private String bikesUrl;

    @Value("${service.cars.url}")
    private String carsUrl;

    public GarageResponseDto addVehicle(Long garageId, Vehicle v) {
        if (!vehicleValidator.exists(v.getVehicleType(), v.getExternalId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Vehicle does not exist", v.getVehicleType(), v.getExternalId()));
        }

        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Garage not found with ID: " + garageId));

        v.setGarage(garage);
        garage.getVehicles().add(v);

        return garageMapper.toGarageResponseDto(garageRepository.save(garage));
    }

    public List<GarageResponseDto> getAllGarage() {
        return garageRepository.findAll().stream()
                .map(garageMapper::toGarageResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<GarageResponseDto> getGarageById(Long id) {
        return garageRepository.findById(id)
                .map(garageMapper::toGarageResponseDto);

    }
}