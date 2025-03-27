package ru.volodin.SarComp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.volodin.SarComp.controller.ClientController;
import ru.volodin.SarComp.entity.Client;
import ru.volodin.SarComp.service.ClientService;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    private Client client;
    private UUID clientId;
/*
    @BeforeEach
    void setUp() {
        clientId = UUID.randomUUID();
        client = new Client();
        client.setId(clientId);
        client.setName("Test Client");
    }

    @Test
    void testAddClient() throws Exception {
        when(clientService.addClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/sarcomp/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId.toString()))
                .andExpect(jsonPath("$.name").value("Test Client"));
    }

    @Test
    void testGetClientById() throws Exception {
        when(clientService.getClientById(clientId)).thenReturn(client);

        mockMvc.perform(get("/sarcomp/clients/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId.toString()))
                .andExpect(jsonPath("$.name").value("Test Client"));
    }

    @Test
    void testGetClientById_NotFound() throws Exception {
        when(clientService.getClientById(clientId)).thenThrow(new RuntimeException("Client not found"));

        mockMvc.perform(get("/sarcomp/clients/{clientId}", clientId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Client not found"));
    }

    @Test
    void testGetAllClients() throws Exception {
        when(clientService.findAllClient()).thenReturn(Collections.singletonList(client));

        mockMvc.perform(get("/sarcomp/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(clientId.toString()))
                .andExpect(jsonPath("$[0].name").value("Test Client"));
    }

    @Test
    void testEditClient() throws Exception {
        when(clientService.updateClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(put("/sarcomp/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId.toString()))
                .andExpect(jsonPath("$.name").value("Test Client"));
    }

    @Test
    void testDeleteClientById() throws Exception {
        doNothing().when(clientService).deleteClientById(clientId);

        mockMvc.perform(delete("/sarcomp/clients/{clientId}", clientId))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteClientById_NotFound() throws Exception {
        doThrow(new RuntimeException("Client not found")).when(clientService).deleteClientById(clientId);

        mockMvc.perform(delete("/sarcomp/clients/{clientId}", clientId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Client not found"));
    }

 */
}
