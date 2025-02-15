package com.example.CarRental.services;

import com.example.CarRental.models.Car;
import com.example.CarRental.repositories.CarsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarsService {

    private final CarsRepository carRepository;
    @Autowired
    public CarsService(CarsRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    public void save(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void update(int id, Car car){
        if (!carRepository.existsById(id)){
            throw new EntityNotFoundException("Car with id " + id + " not found");
        }
        car.setId(id);
        carRepository.save(car);
    }

    @Transactional
    public void delete(int id){
        if (!carRepository.existsById(id)){
            throw new EntityNotFoundException("Car with id " + id + " not found");
        }
        carRepository.deleteById(id);
    }

    public Car findById(int id){
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with ID " + id + " not found"));

    }


}
