package ru.volodin.SarComp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volodin.SarComp.entity.Client;
import ru.volodin.SarComp.service.ClientService;

import java.util.UUID;

@RestController
@RequestMapping("/sarcomp/clients")
@SuppressWarnings({"unused"})
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<?> addClient(@Valid @RequestBody Client client){
        try{
            return ResponseEntity.ok(clientService.addClient(client));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getClientById(@PathVariable(value = "clientId") UUID additionId){
        try {
            return ResponseEntity.ok(clientService.getClientById(additionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllClient(){
        try {
            return ResponseEntity.ok(clientService.findAllClient());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editClient(@Valid @RequestBody Client client){
        try {
            return ResponseEntity.ok(clientService.updateClient(client));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClientById (@PathVariable("clientId") UUID additionId){
        try {
            clientService.deleteClientById(additionId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
