package ru.volodin.SarComp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.volodin.SarComp.controller.LeadController;
import ru.volodin.SarComp.entity.Lead;
import ru.volodin.SarComp.service.LeadService;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LeadController.class)
class LeadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LeadService leadService;


    private final ObjectMapper objectMapper = new ObjectMapper();
/*
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockMvc).build();
    }

    @Test
    void addLead() throws Exception {
        Lead lead = new Lead();
        lead.setName("Test Lead");

        when(leadService.addLead(any(Lead.class))).thenReturn(lead);

        mockMvc.perform(post("/sarcomp/leads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lead)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Lead"));

        verify(leadService, times(1)).addLead(any(Lead.class));
    }

    @Test
    void getLead() throws Exception {
        UUID leadId = UUID.randomUUID();
        Lead lead = new Lead();
        lead.setId(leadId);
        lead.setName("Test Lead");

        when(leadService.getLeadById(leadId)).thenReturn(lead);

        mockMvc.perform(get("/sarcomp/leads/{leadId}", leadId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(leadId.toString()))
                .andExpect(jsonPath("$.name").value("Test Lead"));

        verify(leadService, times(1)).getLeadById(leadId);
    }

    @Test
    void getAllLeads() throws Exception {
        Lead lead = new Lead();
        lead.setName("Test Lead");

        when(leadService.findAllLeads()).thenReturn(Collections.singletonList(lead));

        mockMvc.perform(get("/sarcomp/leads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Lead"));

        verify(leadService, times(1)).findAllLeads();
    }

    @Test
    void editLead() throws Exception {
        Lead lead = new Lead();
        lead.setName("Updated Lead");

        when(leadService.editLead(any(Lead.class))).thenReturn(lead);

        mockMvc.perform(put("/sarcomp/leads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lead)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Lead"));

        verify(leadService, times(1)).editLead(any(Lead.class));
    }

    @Test
    void deleteLeadById() throws Exception {
        UUID leadId = UUID.randomUUID();

        doNothing().when(leadService).deleteLeadById(leadId);

        mockMvc.perform(delete("/sarcomp/leads/{leadId}", leadId))
                .andExpect(status().isOk());

        verify(leadService, times(1)).deleteLeadById(leadId);
    }

 */
}