package com.example.CarRental.controllers;

import com.example.CarRental.dto.CarDTO;
import com.example.CarRental.models.Car;
import com.example.CarRental.services.CarsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarsController {

    private final CarsService carService;
    private final ModelMapper modelMapper;

    @Autowired
    public CarsController(CarsService carService, ModelMapper modelMappe) {
        this.carService = carService;
        this.modelMapper = modelMappe;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CarDTO carDTO) {

        carService.save(convertToCar(carDTO));
        return ResponseEntity.ok("Car created");
    }

    @GetMapping("/findCar/{id}")
    public CarDTO findCar(@PathVariable int id) {
        return convertToCarDTO(carService.findById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update( @PathVariable int id, @Valid @RequestBody CarDTO carDTO) {

        carService.update(id, convertToCar(carDTO));
        return ResponseEntity.ok("Car updated");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        carService.delete(id);
        return ResponseEntity.ok("Car deleted");
    }


    private Car convertToCar(CarDTO personDTO) {
        return modelMapper.map(personDTO, Car.class);
    }

    private CarDTO convertToCarDTO(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }


}
