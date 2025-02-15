package com.example.CarRental.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(max = 50, message = "Name should be up to 50 characters")
    private String name;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @Column(name = "phone")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[0-9]{1,15}$", message = "Phone number must contain 1 to 15 digits")
    private String phone;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> rentalList = new ArrayList<>();

    public Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Client() {

    }


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone( String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }


    public List<Rental> getCarList() {
        return rentalList;
    }

    public void setCarList(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }

    @Override
    public String toString() {
        return "Client { " +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'';
    }

}
