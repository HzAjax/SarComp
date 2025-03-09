package ru.volodin.SarComp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import ru.volodin.SarComp.entity.Photo;
import ru.volodin.SarComp.repository.PhotoRepository;
import ru.volodin.SarComp.service.PhotoService;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PhotoServiceTest {
    @Autowired
    private PhotoService photoService;

    @MockitoBean
    private PhotoRepository photoRepository;

    @TempDir
    Path tempDir; // Временная папка для тестов

    private UUID photoId;
    private Photo photo;
    private MultipartFile mockFile;

    @BeforeEach
    void setUp() throws IOException {
        photoId = UUID.randomUUID();
        photo = Photo.builder().id(photoId).extension(".jpg").build();

        // Создаём мок-файл
        mockFile = mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("test.jpg");
        when(mockFile.getInputStream()).thenReturn(new ByteArrayInputStream("mockData".getBytes()));

        // Меняем путь хранения файлов (чтобы не использовать `D:/JavaProjects/...`)
        ReflectionTestUtils.setField(photoService, "storagePath", tempDir.toString());
    }

/*
    @Test
    void savePhotos_Success() {
        Photo photo = Photo.builder().id(UUID.randomUUID()).extension(".jpg").build();
        when(photoRepository.save(any(Photo.class))).thenReturn(photo);

        Photo savedPhoto = photoService.savePhotos(mockFile);

        assertNotNull(savedPhoto, "Сохранённое фото не должно быть null");
        assertNotNull(savedPhoto.getId(), "ID сохранённого фото не должен быть null");
        verify(photoRepository, times(1)).save(any(Photo.class));
    }
*/

    @Test
    void savePhotos_ThrowsException_WhenFileIsNull() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> photoService.savePhotos(null));
        assertEquals("Фото не было отправлено!", exception.getMessage());
    }

    @Test
    void getPhotoById_Success() throws MalformedURLException {
        when(photoRepository.findById(photoId)).thenReturn(Optional.of(photo));

        Resource resource = photoService.getPhotoById(photoId);

        assertNotNull(resource);
        verify(photoRepository, times(1)).findById(photoId);
    }

    @Test
    void getPhotoById_ThrowsException_WhenPhotoNotFound() {
        when(photoRepository.findById(photoId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> photoService.getPhotoById(photoId));
        assertEquals("Фото в БД не найдено!", exception.getMessage());
    }

    @Test
    void deletePhotoById_Success() throws IOException {
        when(photoRepository.findById(photoId)).thenReturn(Optional.of(photo));

        // Создаём временный файл
        Path filePath = tempDir.resolve(photoId + ".jpg");
        Files.createFile(filePath);

        photoService.deletePhotoById(photoId);

        assertFalse(Files.exists(filePath)); // Проверяем, что файл удалён
        verify(photoRepository, times(1)).deleteById(photoId);
    }

    @Test
    void deletePhotoById_NoError_IfFileNotExists() throws IOException {
        when(photoRepository.findById(photoId)).thenReturn(Optional.of(photo));

        photoService.deletePhotoById(photoId);

        verify(photoRepository, times(1)).deleteById(photoId);
    }
}
