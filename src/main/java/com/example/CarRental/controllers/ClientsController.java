package com.example.CarRental.controllers;

import com.example.CarRental.dto.ClientDTO;
import com.example.CarRental.models.Client;
import com.example.CarRental.services.ClientsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsService clientService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientsController(ClientsService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ClientDTO clientDTO) {
        /*if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );

            return ResponseEntity.badRequest().body(errors);
        }*/

        clientService.create(convertToClient(clientDTO));
        return ResponseEntity.ok("Client created");
    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody ClientDTO clientDTO) {

        clientService.update(id, convertToClient(clientDTO));
        return ResponseEntity.ok("Client updated");
    }

    @GetMapping("/getAll")
    public List<ClientDTO> getAll() {
        return clientService.getAllClients().stream().map(this::convertToClientDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/findClient/{id}")
    public ClientDTO findClient(@PathVariable int id) {
        return convertToClientDTO(clientService.findById(id));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        clientService.delete(id);
        return ResponseEntity.ok("Client deleted");
    }


    private Client convertToClient(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    private ClientDTO convertToClientDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

}
