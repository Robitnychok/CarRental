package com.example.CarRental.controllers;

import com.example.CarRental.dto.RentalDTO;
import com.example.CarRental.models.Car;
import com.example.CarRental.models.Client;
import com.example.CarRental.models.Rental;
import com.example.CarRental.services.CarsService;
import com.example.CarRental.services.ClientsService;
import com.example.CarRental.services.RentalsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

    private final RentalsService rentalsService;
    private final ModelMapper modelMapper;
    private final CarsService carsService;
    private final ClientsService clientsService;

    @Autowired
    public RentalsController(RentalsService rentalsService, ModelMapper modelMapper, CarsService carsService, ClientsService clientsService) {
        this.rentalsService = rentalsService;
        this.modelMapper = modelMapper;
        this.carsService = carsService;
        this.clientsService = clientsService;
    }

    @PostMapping("/rent")
    public ResponseEntity<?> rentCar(@RequestBody RentalDTO rentalDTO) {
        Car car = carsService.findById(rentalDTO.getCarId());
        Client client = clientsService.findById(rentalDTO.getClientId());

        Rental rental = rentalsService.rentCar(client, car, rentalDTO.getRentalDate(), rentalDTO.getReturnDate());
        return ResponseEntity.ok("Car was successfully rented. " +
                "To return, please use this rental number " + rental.getId());

    }

    @PostMapping("/return/{rentalId}")
    public String returnCar(@PathVariable int rentalId) {
        rentalsService.returnCar(rentalId);
        return "Car was successfully returned";
    }

    @GetMapping("/getAllOccupied")
    public List<RentalDTO> getAllOccupied() {
        List<RentalDTO> rentals = rentalsService.getAllOccupied().stream().map(this::convertToRentalDTO)
                .collect(Collectors.toList());
        System.out.println(rentals);
        return rentals;
    }

    @GetMapping("/getAllReturned")
    public List<RentalDTO> getAllReturned() {
        List<RentalDTO> rentals = rentalsService.getAllReturned().stream().map(this::convertToRentalDTO)
                .collect(Collectors.toList());
        return rentals;
    }

    @GetMapping("getAllRentals/{id}")
    public List<RentalDTO> getAllRentals(@PathVariable int id) {
         List<RentalDTO> rentals = rentalsService.getAllRentalsByClient(id).stream().map(this::convertToRentalDTO)
                 .collect(Collectors.toList());
         return rentals;
    }


    private RentalDTO convertToRentalDTO(Rental rental) {
        return modelMapper.map(rental, RentalDTO.class);
    }

}
