package ru.volodin.SarComp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.volodin.SarComp.entity.Client;
import ru.volodin.SarComp.repository.ClientRepository;
import ru.volodin.SarComp.service.ClientService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.volodin.SarComp.ClientServiceConstants.*;

@SpringBootTest
public class ClientServiceTest {
    @Autowired
    protected ClientService clientService;

    @MockitoBean
    protected ClientRepository mockClientRepo;

    @Test
    void findAllClientsTest(){
        List<Client> clientListTest = new ArrayList<>();
        clientListTest.add(client1);
        clientListTest.add(client2);
        clientListTest.get(0).setId(clientTestUUIDOne);
        clientListTest.get(1).setId(clientTestUUIDTwo);

        when(mockClientRepo.findAll()).thenReturn(clientListTest);

        List<Client> clients = clientService.findAllClient();

        assertEquals(clientListTest, clients);
    }

    @Test
    void addClientTest(){
        Client clientTest = new Client();
        clientTest.setId(clientTestUUID);
        clientTest.setName(name);

        when(mockClientRepo.save(clientTest)).thenReturn(clientTest);

        Client client = clientService.addClient(clientTest);

        assertNotNull(client);
        assertEquals(clientTestUUID, client.getId());
        assertEquals(name, client.getName());
    }

    @Test
    void getClientByIdTest(){
        Client clientTest = new Client();
        clientTest.setId(clientTestUUID);

        when(mockClientRepo.findById(clientTestUUID)).thenReturn(Optional.of(clientTest));

        Client client = clientService.getClientById(clientTestUUID);

        assertEquals(clientTest, client);
    }

    @Test
    void getClientByIdEmptyTest(){
        when(mockClientRepo.findById(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            clientService.getClientById(clientTestUUID);
        });
    }

    @Test
    void editClientByIdTest(){
        Client clientTest = new Client();
        clientTest.setId(clientTestUUID);
        clientTest.setName(name);

        when(mockClientRepo.save(clientTest)).thenReturn(clientTest);

        Client client = clientService.updateClient(clientTest);

        assertNotNull(client);
        assertEquals(clientTestUUID, client.getId());
        assertEquals(name, client.getName());
    }

    @Test
    void deleteClientById() {
        UUID clientId = UUID.randomUUID();

        clientService.deleteClientById(clientId);

        verify(mockClientRepo, times(1)).deleteById(clientId);
    }
}
