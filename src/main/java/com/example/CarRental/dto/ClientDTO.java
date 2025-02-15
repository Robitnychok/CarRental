package com.example.CarRental.dto;

import jakarta.validation.constraints.*;

public class ClientDTO {

    @NotEmpty(message = "Name should not be empty")
    @Size(max = 50, message = "Name should be up to 50 characters")
    private String name;

    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @Pattern(regexp = "^[0-9]{1,15}$", message = "Phone number must contain 1 to 15 digits")
    @NotBlank(message = "Phone number cannot be blank")
    private String phone;


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

}
