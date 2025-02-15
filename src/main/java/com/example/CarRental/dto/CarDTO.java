package com.example.CarRental.dto;

import jakarta.validation.constraints.*;

public class CarDTO {

    @NotEmpty(message = "Make should not be empty")
    @Size(max = 50, message = "Make should be between up to 50 characters")
    private String make;

    @NotEmpty(message = "Model should not be empty")
    @Size(max = 50, message = "Model should be between up to 50 characters")
    private String model;

    @Min(value = 1900, message = "Year should be more than 1900")
    @Max(value = 2025, message = "Year should be up to than 2025")
    @NotNull(message = "Year should not be empty")
    private int year;

    @NotEmpty(message = "Color should not be empty")
    @Size(max = 50, message = "Color should be between up to 50 characters")
    private String color;

    @NotNull(message = "Mileage should not be empty")
    private double mileage;

    public CarDTO() {

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

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }
}
