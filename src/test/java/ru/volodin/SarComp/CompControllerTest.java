package ru.volodin.SarComp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.volodin.SarComp.controller.CompController;
import ru.volodin.SarComp.entity.Comp;
import ru.volodin.SarComp.service.CompService;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompController.class)
public class CompControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CompService compService;

    private Comp comp;
    private UUID compId;

    @BeforeEach
    void setUp() {
        compId = UUID.randomUUID();
        comp = new Comp();
        comp.setId(compId);
        comp.setName("Test Comp");
    }

    @Test
    void testAddComp() throws Exception {
        when(compService.addComp(any(Comp.class))).thenReturn(comp);

        mockMvc.perform(post("/sarcomp/comps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + compId + "\", \"name\":\"Test Comp\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(compId.toString()))
                .andExpect(jsonPath("$.name").value("Test Comp"));
    }

    @Test
    void testGetCompById() throws Exception {
        when(compService.getCompById(compId)).thenReturn(comp);

        mockMvc.perform(get("/sarcomp/comps/{id}", compId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(compId.toString()))
                .andExpect(jsonPath("$.name").value("Test Comp"));
    }

    @Test
    void testGetCompById_NotFound() throws Exception {
        when(compService.getCompById(compId)).thenThrow(new RuntimeException("Comp not found"));

        mockMvc.perform(get("/sarcomp/comps/{id}", compId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Comp not found"));
    }

    @Test
    void testGetAllComps() throws Exception {
        when(compService.findAllComp()).thenReturn(Collections.singletonList(comp));

        mockMvc.perform(get("/sarcomp/comps"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(compId.toString()))
                .andExpect(jsonPath("$[0].name").value("Test Comp"));
    }

    @Test
    void testEditComp() throws Exception {
        when(compService.updateComp(any(Comp.class))).thenReturn(comp);

        mockMvc.perform(put("/sarcomp/comps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + compId + "\", \"name\":\"Updated Comp\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(compId.toString()))
                .andExpect(jsonPath("$.name").value("Test Comp"));
    }

    @Test
    void testDeleteCompById() throws Exception {
        doNothing().when(compService).deleteCompById(compId);

        mockMvc.perform(delete("/sarcomp/comps/{id}", compId))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCompById_NotFound() throws Exception {
        doThrow(new RuntimeException("Comp not found")).when(compService).deleteCompById(compId);

        mockMvc.perform(delete("/sarcomp/comps/{id}", compId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Comp not found"));
    }
}
