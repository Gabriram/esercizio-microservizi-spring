package com.corso.cars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.corso.cars.repository.CarsRepository;
import com.corso.cars.CarsMapper;
import com.corso.cars.dtos.CarsResponseDto;
import com.corso.cars.entity.Cars;

@Service
public class CarsService {

    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    private CarsMapper carsMapper;

    public List<CarsResponseDto> getAllCars() {
        return carsRepository.findAll().stream()
                .map(car -> carsMapper.toCarsResponseDto(car))
                .collect(Collectors.toList());
    }

    public Optional<CarsResponseDto> getCarById(Long id) {
        return carsRepository.findById(id)
                .map(car -> carsMapper.toCarsResponseDto(car));
    }

    public CarsResponseDto save(Cars dto) {
        Cars car = carsRepository.save(dto);
        return carsMapper.toCarsResponseDto(car);
    }

    public void delete(Long id) {
        carsRepository.deleteById(id);
    }

    public CarsResponseDto update(Long id, Cars dto) {
        return carsRepository.findById(id).map(existingCar -> {
            existingCar.setBrand(dto.getBrand());
            existingCar.setModel(dto.getModel());
            existingCar.setFuelType(dto.getFuelType());
            existingCar.setDoors(dto.getDoors());
            existingCar.setSeats(dto.getSeats());
            existingCar.setYear(dto.getYear());
            existingCar.setPrice(dto.getPrice());
            return carsMapper.toCarsResponseDto(carsRepository.save(existingCar));
        }).orElseThrow(() -> new RuntimeException("Car not found"));
    }

}
