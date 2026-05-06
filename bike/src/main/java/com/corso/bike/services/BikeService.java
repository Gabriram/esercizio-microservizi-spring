package com.corso.bike.services;

import com.corso.bike.repository.BikeRepository;

import lombok.RequiredArgsConstructor;

import com.corso.bike.entity.Bike;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BikeService {

    private final BikeRepository bikeRepository;

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public Optional<Bike> getBikeById(Long id) {
        return bikeRepository.findById(id);
    }

    public Bike save(Bike bike) {
        return bikeRepository.save(bike);
    }

    public void delete(Long id) {
        bikeRepository.deleteById(id);
    }

    public Bike update(Long id, Bike bike) {
        return bikeRepository.findById(id).map(existingBike -> {
            existingBike.setBrand(bike.getBrand());
            existingBike.setModel(bike.getModel());
            existingBike.setEngCc(bike.getEngCc());
            existingBike.setType(bike.getType());
            existingBike.setYear(bike.getYear());
            existingBike.setPrice(bike.getPrice());
            return bikeRepository.save(existingBike);
        }).orElseThrow(() -> new RuntimeException("Bike not found with id " + id));
    }

}
