package ru.volodin.SarComp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.volodin.SarComp.controller.AdditionController;
import ru.volodin.SarComp.entity.Addition;
import ru.volodin.SarComp.service.AdditionService;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdditionController.class)
public class AdditionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AdditionService additionService;

    private Addition addition;
    private UUID additionId;
/*
    @BeforeEach
    void setUp() {
        additionId = UUID.randomUUID();
        addition = new Addition();
        addition.setId(additionId);
        addition.setName("Test Addition");
    }

    @Test
    void testAddAddition() throws Exception {
        when(additionService.addAddition(any(Addition.class))).thenReturn(addition);

        mockMvc.perform(post("/sarcomp/additions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + additionId + "\", \"name\":\"Test Addition\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(additionId.toString()))
                .andExpect(jsonPath("$.name").value("Test Addition"));
    }

    @Test
    void testGetAdditionById() throws Exception {
        when(additionService.getAdditionById(additionId)).thenReturn(addition);

        mockMvc.perform(get("/sarcomp/additions/{id}", additionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(additionId.toString()))
                .andExpect(jsonPath("$.name").value("Test Addition"));
    }

    @Test
    void testGetAdditionById_NotFound() throws Exception {
        when(additionService.getAdditionById(additionId)).thenThrow(new RuntimeException("Addition not found"));

        mockMvc.perform(get("/sarcomp/additions/{id}", additionId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Addition not found"));
    }

    @Test
    void testGetAllAdditions() throws Exception {
        when(additionService.findAllAddition()).thenReturn(Collections.singletonList(addition));

        mockMvc.perform(get("/sarcomp/additions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(additionId.toString()))
                .andExpect(jsonPath("$[0].name").value("Test Addition"));
    }

    @Test
    void testEditAddition() throws Exception {
        when(additionService.updateAddition(any(Addition.class))).thenReturn(addition);

        mockMvc.perform(put("/sarcomp/additions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + additionId + "\", \"name\":\"Updated Addition\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(additionId.toString()))
                .andExpect(jsonPath("$.name").value("Test Addition"));
    }

    @Test
    void testDeleteAdditionById() throws Exception {
        doNothing().when(additionService).deleteAdditionById(additionId);

        mockMvc.perform(delete("/sarcomp/additions/{id}", additionId))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAdditionById_NotFound() throws Exception {
        doThrow(new RuntimeException("Addition not found")).when(additionService).deleteAdditionById(additionId);

        mockMvc.perform(delete("/sarcomp/additions/{id}", additionId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Addition not found"));
    }
 */
}
