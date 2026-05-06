package com.corso.cars;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corso.cars.dtos.CarsModifyDataDTO;
import com.corso.cars.dtos.CarsResponseDto;
import com.corso.cars.entity.Cars;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import com.corso.cars.services.CarsService;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class Controller {

    private final CarsService carsService;
    private final CarsMapper carsMapper;

    public Controller(CarsService carsService, CarsMapper carsMapper) {
        this.carsMapper = carsMapper;
        this.carsService = carsService;
    }

    @GetMapping()
    public List<CarsResponseDto> getAll() {
        return carsService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarsResponseDto> getById(@PathVariable Long id) {
        Optional<CarsResponseDto> car = carsService.getCarById(id);
        return car.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarsResponseDto> create(@RequestBody CarsModifyDataDTO dto) {
        Cars car = carsMapper.toCarEntity(dto);
        CarsResponseDto created = carsService.save(car);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarsResponseDto> update(@PathVariable Long id, @RequestBody CarsModifyDataDTO carDto) {
        Cars car = carsMapper.toCarEntity(carDto);
        CarsResponseDto updated = carsService.update(id, car);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
