package com.corso.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.corso.cars.entity.Cars;

public interface CarsRepository extends JpaRepository<Cars, Long> {

}
