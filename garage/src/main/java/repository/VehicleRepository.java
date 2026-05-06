package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Long, Vehicle> {

}
