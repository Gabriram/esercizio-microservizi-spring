package com.corso.bike.services;

import com.corso.bike.repository.BikeRepository;
import com.corso.bike.BikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.corso.bike.dtos.*;
import com.corso.bike.entity.*;

@Service
@RequiredArgsConstructor
public class BikeService {

    private final BikeRepository bikeRepository;
    private final BikeMapper bikeMapper;

    public List<BikeResponseDto> getAllBikes() {
        return bikeRepository.findAll().stream()
                .map(bikeMapper::toBikeResponseDto) // Replaced lambda with method reference
                .collect(Collectors.toList());
    }

    public Optional<BikeResponseDto> getBikeById(Long id) {
        return bikeRepository.findById(id)
                .map(bikeMapper::toBikeResponseDto); // Replaced lambda with method reference
    }

    public BikeResponseDto save(Bike dto) {
        Bike savedBike = bikeRepository.save(dto);
        return bikeMapper.toBikeResponseDto(savedBike);
    }

    public void delete(Long id) {
        bikeRepository.deleteById(id);
    }

    public BikeResponseDto update(Long id, Bike dto) {
        return bikeRepository.findById(id).map(existingBike -> {
            existingBike.setBrand(dto.getBrand());
            existingBike.setModel(dto.getModel());
            existingBike.setEngCc(dto.getEngCc());
            existingBike.setType(dto.getType());
            existingBike.setYear(dto.getYear());
            existingBike.setPrice(dto.getPrice());
            return bikeMapper.toBikeResponseDto(bikeRepository.save(existingBike));
        }).orElseThrow(() -> new RuntimeException("Bike not found with id " + id));
    }

}