package com.corso.garage;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.corso.garage.dtos.GarageModifyDataDto;
import com.corso.garage.dtos.VehicleModifyDataDto;
import com.corso.garage.dtos.VehicleResponseDto;
import com.corso.garage.dtos.GarageResponseDto;
import com.corso.garage.entities.Garage;
import com.corso.garage.services.GarageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/garage")
@CrossOrigin("*")
public class Controller {

    private final GarageService garageService;
    private final GarageMapper garageMapper;

    public Controller(GarageService garageService, GarageMapper garageMapper) {
        this.garageService = garageService;
        this.garageMapper = garageMapper;

    }

    @GetMapping("/api/garage")
    public List<GarageResponseDto> getAllGarage() {
        return garageService.getAllGarage();
    }

    @GetMapping("/api/garage/{id}")
    public ResponseEntity<GarageResponseDto> getGarageById(@PathVariable Long id) {
        Optional<GarageResponseDto> garage = garageService.getGarageById(id);
        return garage.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/garage")
    public ResponseEntity<GarageResponseDto> createGarage(@RequestBody GarageModifyDataDto garageDto) {
        Garage garage = garageMapper.toGarageEntity(garageDto);
        GarageResponseDto created = garageService.createGarage(garage);
        return ResponseEntity.ok(created);

    }

}