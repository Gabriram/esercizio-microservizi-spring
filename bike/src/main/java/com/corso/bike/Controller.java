package com.corso.bike;

import com.corso.bike.dtos.BikeDto;
import com.corso.bike.entity.Bike;
import com.corso.bike.services.BikeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/bikes")
@CrossOrigin(origins = "*")
public class Controller {

    private final BikeService service;

    public Controller(BikeService service) {
        this.service = service;
    }

    @GetMapping
    public List<BikeDto> getAll() {
        return service.findAll().stream()
                .map(BikeDto::fromBike)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeDto> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(BikeDto::fromBike)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BikeDto> create(@RequestBody BikeDto bike) {
        Bike saved = service.save(Bike.fromDto(bike));
        return ResponseEntity.status(201).body(BikeDto.fromBike(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeDto> update(@PathVariable Long id, @RequestBody BikeDto bikeDto) {
        Bike bike = Bike.fromDto(bikeDto);
        return ResponseEntity.ok(BikeDto.fromBike(service.update(id, bike)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
