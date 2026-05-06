package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.Garage;

public interface GarageRepository extends JpaRepository<Long, Garage> {

}
