package com.corso.bike;

import com.corso.bike.entity.Bike;
import com.corso.bike.services.BikeService;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public List<Bike> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bike> create(@RequestBody Bike bike) {
        return ResponseEntity.status(201).body(service.save(bike));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bike> update(@PathVariable Long id, @RequestBody Bike bike) {
        return ResponseEntity.ok(service.update(id, bike));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

}
