package com.corso.garage;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.corso.garage.dtos.GarageModifyDataDto;
import com.corso.garage.dtos.VehicleModifyDataDto;
import com.corso.garage.dtos.VehicleResponseDto;
import com.corso.garage.dtos.GarageResponseDto;
import com.corso.garage.entities.Garage;
import com.corso.garage.entities.Vehicle;
import com.corso.garage.services.GarageService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/garages")
@CrossOrigin("*")
@RequiredArgsConstructor
public class Controller {

    private final GarageService garageService;

    private final GarageMapper garageMapper;

    @GetMapping
    public List<GarageResponseDto> getAllGarage() {
        return garageService.getAllGarage();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarageResponseDto> getGarageById(@PathVariable Long id) {
        Optional<GarageResponseDto> garage = garageService.getGarageById(id);
        return garage.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GarageResponseDto> createGarage(@RequestBody GarageModifyDataDto garageDto) {
        Garage garage = garageMapper.toGarageEntity(garageDto);
        GarageResponseDto created = garageService.save(garage);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarageResponseDto> updateGarage(@PathVariable Long id,
            @RequestBody GarageModifyDataDto garageDto) {
        Garage garage = garageMapper.toGarageEntity(garageDto);
        GarageResponseDto updated = garageService.update(id, garage);
        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarage(@PathVariable Long id) {
        garageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/vehicles")
    public ResponseEntity<GarageResponseDto> addVehicle(@PathVariable Long id,
            @RequestBody VehicleModifyDataDto vehicleDto) {
        Vehicle vehicle = garageMapper.toVehicleEntity(vehicleDto);
        GarageResponseDto updatedGarage = garageService.addVehicle(id, vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedGarage);
    }

    @GetMapping("/{garageId}/vehicles/{vehicleId}")
    public ResponseEntity<VehicleResponseDto> getVehicle(@PathVariable Long garageId, @PathVariable Long vehicleId) {
        Optional<VehicleResponseDto> vehicle = garageService.getVehicleById(garageId, vehicleId);
        return vehicle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}