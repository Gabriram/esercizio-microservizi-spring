package com.corso.bike.services;

import com.corso.bike.repository.BikeRepository;
import com.corso.bike.entity.Bike;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    private final BikeRepository repository;

    public BikeService(BikeRepository repository) {
        this.repository = repository;
    }

    public List<Bike> findAll() {
        return repository.findAll();
    }

    public Optional<Bike> findById(Long id) {
        return repository.findById(id);
    }

    public Bike save(Bike bike) {
        return repository.save(bike);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Bike update(Long id, Bike updated) {
        return repository.findById(id)
                .map(bike -> {
                    bike.setBrand(updated.getBrand());
                    bike.setModel(updated.getModel());
                    bike.setEngCc(updated.getEngCc());
                    bike.setType(updated.getType());
                    bike.setYear(updated.getYear());
                    bike.setPrice(updated.getPrice());
                    return repository.save(bike);
                })
                .orElseThrow(() -> new RuntimeException("Bike not found with id " + id));
    }

}
