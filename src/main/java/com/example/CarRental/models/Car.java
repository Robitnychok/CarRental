package com.example.CarRental.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "car")
public class Car {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "make")
    @NotEmpty(message = "Make should not be empty")
    @Size(max = 50, message = "Make should be between up to 50 characters")
    private String make;

    @Column(name = "model")
    @NotEmpty(message = "Model should not be empty")
    @Size(max = 50, message = "Model should be between up to 50 characters")
    private String model;

    @Column(name = "year")
    @Min(value = 1900, message = "Year should be more than 1900")
    @Max(value = 2025, message = "Year should be up to than 2025")
    @NotNull(message = "Year should not be empty")
    private int year;

    @Column(name = "color")
    @NotEmpty(message = "Color should not be empty")
    @Size(max = 50, message = "Color should be between up to 50 characters")
    private String color;

    @Column(name = "mileage")
    @NotNull(message = "Mileage should not be empty")
    private double mileage;

//    @OneToMany(mappedBy = "car")
//    private List<Rental> rentals = new ArrayList<>();

    public Car(String make, String model, int year, String color, int mileage) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Car { " +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
