package com.corso.garage.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.corso.garage.GarageMapper;
import com.corso.garage.dtos.GarageResponseDto;
import com.corso.garage.entities.Garage;
import com.corso.garage.entities.Vehicle;
import com.corso.garage.repository.GarageRepository;
import com.corso.garage.dtos.VehicleResponseDto;

@Service
@RequiredArgsConstructor
@Transactional
public class GarageService {

    private final VehicleValidator vehicleValidator;
    private final GarageRepository garageRepository;
    private final GarageMapper garageMapper;

    @Value("${services.bikes.url}")
    private String bikesUrl;

    @Value("${services.cars.url}")
    private String carsUrl;

    public GarageResponseDto addVehicle(Long garageId, Vehicle v) {
        if (!vehicleValidator.exists(v.getVehicleType(), v.getExternalId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Vehicle does not exist: %s, %d", v.getVehicleType(), v.getExternalId()));
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

    public GarageResponseDto save(Garage garage) {
        Garage savedGarage = garageRepository.save(garage);
        return garageMapper.toGarageResponseDto(savedGarage);
    }

    public void delete(Long id) {
        garageRepository.deleteById(id);
    }

    public GarageResponseDto update(Long id, Garage garage) {
        return garageRepository.findById(id).map(existingGarage -> {
            existingGarage.setOwnerName(garage.getOwnerName());
            existingGarage.setAddress(garage.getAddress());
            return garageMapper.toGarageResponseDto(garageRepository.save(existingGarage));
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found with ID: " + id));
    }

    public Optional<VehicleResponseDto> getVehicleById(Long garageId, Long vehicleId) {
        return garageRepository.findById(garageId)
                .flatMap(garage -> garage.getVehicles().stream()
                        .filter(vehicle -> vehicle.getId().equals(vehicleId))
                        .findFirst())
                .map(garageMapper::toVehicleResponseDto);
    }

}
