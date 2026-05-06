package com.corso.bike.services;

import com.corso.bike.repository.BikeRepository;
import com.corso.bike.BikeMapper;

import lombok.RequiredArgsConstructor;

import com.corso.bike.dtos.BikeResponseDto;
import com.corso.bike.entity.Bike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private BikeMapper bikeMapper;

    public List<BikeResponseDto> getAllBikes() {
        return bikeRepository.findAll().stream()
                .map(bike -> bikeMapper.toBikeResponseDto(bike))
                .collect(Collectors.toList());
    }

    public Optional<BikeResponseDto> getBikeById(Long id) {
        return bikeRepository.findById(id)
                .map(bike -> bikeMapper.toBikeResponseDto(bike));
    }

    public BikeResponseDto save(Bike dto) {
        Bike bike = bikeRepository.save(dto);
        return bikeMapper.toBikeResponseDto(bikeRepository.save(bike));
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
