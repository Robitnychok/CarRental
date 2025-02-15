package com.example.CarRental.repositories;

import com.example.CarRental.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientsRepository extends JpaRepository<Client, Long> {
    Optional<Client> findById(int id);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsById(int id);
    void deleteById(int id);
}
