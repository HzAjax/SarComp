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
import ru.volodin.SarComp.controller.ComponentController;
import ru.volodin.SarComp.entity.components.Component;
import ru.volodin.SarComp.service.ComponentService;


import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ComponentController.class)
class ComponentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ComponentService componentService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockMvc).build();
    }

    @Test
    void addComponent() throws Exception {
        Component component = new Component();
        component.setName("Test Component");

        when(componentService.addComponent(any(Component.class))).thenReturn(component);

        mockMvc.perform(post("/sarcomp/components")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(component)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Component"));

        verify(componentService, times(1)).addComponent(any(Component.class));
    }
/*
    @Test
    void getAllComponent() throws Exception {
        ComponentType componentType = ComponentType.MEM;
        Component component = new Component();
        component.setName("Test Component");

        when(componentService.findAll(componentType)).thenReturn(Collections.singletonList(component));

        mockMvc.perform(get("/sarcomp/components/{componentType}", componentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Component"));

        verify(componentService, times(1)).findAll(componentType);
    }

    @Test
    void getComponentById() throws Exception {
        UUID componentId = UUID.randomUUID();
        Component component = new Component();
        component.setId(componentId);
        component.setName("Test Component");

        when(componentService.getComponentById(componentId)).thenReturn(component);

        mockMvc.perform(get("/sarcomp/components")
                        .param("componentId", componentId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(componentId.toString()))
                .andExpect(jsonPath("$.name").value("Test Component"));

        verify(componentService, times(1)).getComponentById(componentId);
    }
*/
    @Test
    void editComponent() throws Exception {
        Component component = new Component();
        component.setName("Updated Component");

        when(componentService.addComponent(any(Component.class))).thenReturn(component);

        mockMvc.perform(put("/sarcomp/components")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(component)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Component"));

        verify(componentService, times(1)).addComponent(any(Component.class));
    }

    @Test
    void deleteComponentById() throws Exception {
        UUID componentId = UUID.randomUUID();

        doNothing().when(componentService).deleteComponentById(componentId);

        mockMvc.perform(delete("/sarcomp/components/{componentId}", componentId))
                .andExpect(status().isOk());

        verify(componentService, times(1)).deleteComponentById(componentId);
    }
}