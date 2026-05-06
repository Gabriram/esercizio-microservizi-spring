package com.corso.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corso.garage.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
