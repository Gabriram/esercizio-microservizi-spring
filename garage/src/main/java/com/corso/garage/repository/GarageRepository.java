package com.corso.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.corso.garage.entities.Garage;

public interface GarageRepository extends JpaRepository<Garage, Long> {

}
