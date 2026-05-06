package com.corso.bike;

import com.corso.bike.dtos.BikeModifyDTO;
import com.corso.bike.dtos.BikeResponseDto;
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
    public List<BikeResponseDto> getAll() {
        return bikeService.getAllBikes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeResponseDto> getById(@PathVariable Long id) {
        Optional<BikeResponseDto> bike = bikeService.getBikeById(id);
        return bike.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BikeResponseDto> create(@RequestBody BikeModifyDTO bikeDto) {
        Bike bike = bikeMapper.toBikeEntity(bikeDto);
        BikeResponseDto created = bikeService.save(bike);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeResponseDto> update(@PathVariable Long id, @RequestBody BikeModifyDTO bikeDto) {
        Bike bike = bikeMapper.toBikeEntity(bikeDto);
        BikeResponseDto updated = bikeService.update(id, bike);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bikeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
