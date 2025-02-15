package com.example.CarRental.repositories;

import com.example.CarRental.models.Rental;
import com.example.CarRental.models.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByStatus(RentalStatus rentalStatus);
    List<Rental> findByClientId(int clientId);
    boolean existsByClientId(int id);

}
