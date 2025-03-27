package ru.volodin.SarComp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.volodin.SarComp.controller.PhotoController;
import ru.volodin.SarComp.service.PhotoService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PhotoController.class)
class PhotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PhotoService photoService;
/*
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockMvc).build();
    }

    @Test
    void addPhoto() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test image content".getBytes());

        when(photoService.savePhotos(any(MultipartFile.class))).thenReturn("Photo saved successfully");

        mockMvc.perform(multipart("/sarcomp/photos").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("Photo saved successfully"));

        verify(photoService, times(1)).savePhotos(any(MultipartFile.class));
    }

    @Test
    void getPhoto() throws Exception {
        UUID photoId = UUID.randomUUID();
        Resource resource = mock(Resource.class);
        when(resource.exists()).thenReturn(true);
        when(resource.isReadable()).thenReturn(true);
        when(resource.getFilename()).thenReturn("test.jpg");
        when(photoService.getPhotoById(photoId)).thenReturn(resource);
        when(photoService.getMimetypeFromFilename("test.jpg")).thenReturn(MediaType.IMAGE_JPEG_VALUE);

        mockMvc.perform(get("/sarcomp/photos/{photoId}", photoId))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"test.jpg\""))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE))
                .andExpect(content().string(""));

        verify(photoService, times(1)).getPhotoById(photoId);
    }

    @Test
    void getPhotoNotFound() throws Exception {
        UUID photoId = UUID.randomUUID();
        Resource resource = mock(Resource.class);
        when(resource.exists()).thenReturn(false);
        when(photoService.getPhotoById(photoId)).thenReturn(resource);

        mockMvc.perform(get("/sarcomp/photos/{photoId}", photoId))
                .andExpect(status().isNotFound());

        verify(photoService, times(1)).getPhotoById(photoId);
    }

    @Test
    void deletePhotoById() throws Exception {
        UUID photoId = UUID.randomUUID();

        doNothing().when(photoService).deletePhotoById(photoId);

        mockMvc.perform(delete("/sarcomp/photos/{photoId}", photoId))
                .andExpect(status().isOk());

        verify(photoService, times(1)).deletePhotoById(photoId);
    }

 */
}