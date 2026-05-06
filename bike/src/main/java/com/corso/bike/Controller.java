package com.corso.bike;

import com.corso.bike.dtos.BikeCreateDto;
import com.corso.bike.dtos.BikeResponseDto;
import com.corso.bike.dtos.BikeUpdateRequestDto;
import com.corso.bike.entity.Bike;
import com.corso.bike.services.BikeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/bikes")
@CrossOrigin(origins = "*")
public class Controller {

    private final BikeService bikeService;
    private final BikeMapper bikeMapper;

    public Controller(BikeService bikeService, BikeMapper bikeMapper) {
        this.bikeService = bikeService;
        this.bikeMapper = bikeMapper;
    }

    @GetMapping
    public List<Bike> getAll() {
        return bikeService.getAllBikes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeResponseDto> getById(@PathVariable Long id) {
        Optional<Bike> bike = bikeService.getBikeById(id);
        return bike.map(value -> ResponseEntity.ok(bikeMapper.toResponseDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BikeResponseDto> create(@RequestBody BikeCreateDto bike) {
        Bike saved = bikeService.save(bikeMapper.toBikeEntity(bike));
        return ResponseEntity.status(201).body(bikeMapper.toResponseDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeResponseDto> update(@PathVariable Long id, @RequestBody BikeUpdateRequestDto bikeDto) {
        Bike bike = bikeMapper.toBikeEntity(bikeDto);
        Bike updated = bikeService.update(id, bike);
        return ResponseEntity.ok(bikeMapper.toResponseDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bikeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
