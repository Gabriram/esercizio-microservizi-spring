package com.corso.bike;

import com.corso.bike.dtos.BikeModifyDTO;
import com.corso.bike.dtos.BikeResponseDTO;
import com.corso.bike.entity.Bike;
import com.corso.bike.services.BikeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestBody;

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
    public List<BikeResponseDTO> getAll() {
        return bikeService.getAllBikes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeResponseDTO> getById(@PathVariable Long id) {
        Optional<BikeResponseDTO> bike = bikeService.getBikeById(id);
        return bike.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BikeResponseDTO> create(@RequestBody BikeModifyDTO bikeDto) {
        Bike bike = bikeMapper.toBikeEntity(bikeDto);
        BikeResponseDTO created = bikeService.save(bike);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeResponseDTO> update(@PathVariable Long id, @RequestBody BikeModifyDTO bikeDto) {
        Bike bike = bikeMapper.toBikeEntity(bikeDto);
        BikeResponseDTO updated = bikeService.update(id, bike);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bikeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
