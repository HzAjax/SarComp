package ru.volodin.SarComp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.entity.Client;
import ru.volodin.SarComp.repository.ClientRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@SuppressWarnings({"unused"})
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAllClient(){
        return clientRepository.findAll();
    }

    public Client addClient(Client client){
        return clientRepository.save(client);
    }

    public Client getClientById(UUID id){
        return clientRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Услуга не была найдена!"));
    }

    public Client updateClient(Client client){
        return clientRepository.save(client);
    }

    public void deleteClientById(UUID id){
        clientRepository.deleteById(id);
    }
}
