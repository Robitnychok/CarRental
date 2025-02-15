package com.example.CarRental.services;

import com.example.CarRental.models.Client;
import com.example.CarRental.repositories.ClientsRepository;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class ClientsService {

    private final ClientsRepository clientRepository;
    @Autowired
    public ClientsService(ClientsRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void create(Client client){

        if (clientRepository.existsByPhone(client.getPhone())) {
            throw new IllegalArgumentException("Phone number already exists.");
        }

        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }
        clientRepository.save(client);
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client findById(int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client with ID " + id + " not found"));
    }

    @Transactional
    public void update(int id, Client client){
//        Assert.isTrue(!clientRepository.existsById(id), "Client with ID " + id + " already exists.");
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client with ID " + id + " not found");
        }

        if (clientRepository.existsByPhone(client.getPhone()) &&
                !clientRepository.findById(id).map(Client::getPhone).orElse("").equals(client.getPhone())) {
            throw new IllegalArgumentException("Phone number already exists.");
        }

        if (clientRepository.existsByEmail(client.getEmail()) &&
                !clientRepository.findById(id).map(Client::getEmail).orElse("").equals(client.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        client.setId(id);
        clientRepository.save(client);
    }

    @Transactional
    public void delete(int id) {

        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client with ID " + id + " not found");
        }
        clientRepository.deleteById(id);
    }

}
