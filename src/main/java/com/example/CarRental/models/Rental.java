package com.example.CarRental.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental")
public class Rental {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "rental_date")
    private LocalDateTime rentalDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    public Rental(Client client, Car car, LocalDateTime rentalDate, LocalDateTime returnDate, RentalStatus status) {
        this.client = client;
        this.car = car;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public Rental() {
    }

    public Rental(Client client, Car car, LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.client = client;
        this.car = car;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public Rental(Client client, Car car, LocalDateTime returnDate) {
        this.client = client;
        this.car = car;
        this.rentalDate = LocalDateTime.now();
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "client=" + client.getName() +
                ", car=" + car.getModel() +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", status=" + status +
                '}';
    }
}
