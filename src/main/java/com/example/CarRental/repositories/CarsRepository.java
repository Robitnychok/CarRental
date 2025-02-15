package com.example.CarRental.repositories;

import com.example.CarRental.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarsRepository extends JpaRepository<Car, Long> {

    void deleteById(int id);
    Optional<Car> findById(int id);
    boolean existsById(int id);
}
