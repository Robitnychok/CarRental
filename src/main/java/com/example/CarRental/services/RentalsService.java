package com.example.CarRental.services;

import com.example.CarRental.models.Car;
import com.example.CarRental.models.Client;
import com.example.CarRental.models.Rental;
import com.example.CarRental.models.RentalStatus;
import com.example.CarRental.repositories.RentalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalsService {

    private final RentalRepository rentalRepository;
    @Autowired
    public RentalsService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Transactional
    public Rental rentCar(Client client, Car car, LocalDateTime rentalDate, LocalDateTime returnDate){
        Rental rental = new Rental(client, car, rentalDate, returnDate);

        for (Rental r : rentalRepository.findAll()) {
            if(r.getCar().equals(car) && r.getStatus() == RentalStatus.OCCUPIED){
                throw new IllegalArgumentException("Car is already rented");
            }
        }
        rental.setStatus(RentalStatus.OCCUPIED);
        rental.setRentalDate(LocalDateTime.now());
        rental.setReturnDate(null);

        rentalRepository.save(rental);
        return rental;
    }

    @Transactional
    public void returnCar(long rentalId){
        Rental rental = rentalRepository.findById(rentalId).
                orElseThrow(() -> new EntityNotFoundException("Rental not found with ID: " + rentalId));;

        rental.setReturnDate(LocalDateTime.now());
        rental.setStatus(RentalStatus.RETURNED);
        rentalRepository.save(rental);
    }

    public List<Rental> getAllOccupied(){
         return rentalRepository.findByStatus(RentalStatus.OCCUPIED);
    }

    public List<Rental> getAllReturned(){
        return rentalRepository.findByStatus(RentalStatus.RETURNED);
    }

    public List<Rental> getAllRentalsByClient(int clientId){
        if (!rentalRepository.existsByClientId(clientId)){
            throw new IllegalArgumentException("Client not found");
        }

       return rentalRepository.findByClientId(clientId);
    }


}
