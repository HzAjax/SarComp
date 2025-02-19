package ru.volodin.SarComp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.volodin.SarComp.entity.Photo;
import ru.volodin.SarComp.entity.enums.FileExtension;
import ru.volodin.SarComp.repository.PhotoRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Service
@SuppressWarnings({"unused"})
public class PhotoService {

    private String storagePath = "D:/JavaProjects/SarComp/src/main/resources/storage/photos";

    @Autowired
    @SuppressWarnings({"unused"})
    private PhotoRepository photoRepository;

    public Photo savePhotos(MultipartFile file) {
        if (file == null) {
            throw new IllegalStateException("Фото не было отправлено!");
        } else {
            Photo savedPhoto = null;
            try {
                Photo photo = Photo.builder()
                        .extension(getExtensionFromMimetype(file))
                        .build();
                photo = photoRepository.save(photo);
                saveImage(file, photo);
                savedPhoto = photo;
            } catch (Exception e) {
                log.error("Unavailable to save file ({})", file.getOriginalFilename());
            }
        return savedPhoto;
        }
    }

    public Resource getPhotoById(UUID id) throws MalformedURLException {
        Optional<Photo> photoIfPresent = photoRepository.findById(id);
        if (photoIfPresent.isEmpty()) {
            throw new NoSuchElementException("Фото в БД не найдено!");
        } else {
            Path resultPath = Paths.get(storagePath, getFilenameWithExt(photoIfPresent.get()));
            return new UrlResource(resultPath.toUri());
        }
    }

    public void deletePhotoById(UUID id) throws IOException {
        Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isPresent()) {
            Path resultPath = Paths.get(storagePath, getFilenameWithExt(photo.get()));
            Files.deleteIfExists(resultPath);
            photoRepository.deleteById(id);
        }
    }

    public String getMimetypeFromFilename(String filename) {
        String extension = getExtensionFromFilename(filename);

        Optional<FileExtension> ext = Arrays.stream(FileExtension.values())
                .filter(e -> e.getExtension().equalsIgnoreCase(extension))
                .findFirst();

        if (ext.isPresent()) {
            return ext.get().getMimeType();
        } else {
            throw new IllegalStateException("Расширение не поддерживается");
        }
    }

    private void saveImage(MultipartFile file, Photo photo) throws IOException {
        Path resultPath = Paths.get(storagePath, getFilenameWithExt(photo));
        Files.createDirectories(resultPath.getParent());
        file.transferTo(resultPath);
    }

    private String getExtensionFromMimetype(MultipartFile file) {
        Optional<FileExtension> ext = Arrays.stream(FileExtension.values())
                .filter(e -> e.getMimeType().equalsIgnoreCase(file.getContentType()))
                .findFirst();

        if (ext.isPresent()) {
            return ext.get().getExtension();
        } else {
            throw new IllegalStateException("Расширение не поддерживается");
        }
    }

    private String getFilenameWithExt (Photo photo) {
        return photo.getId() + "." + photo.getExtension();
    }

    private String getExtensionFromFilename(String fileName) {
        Path path = Paths.get(fileName);
        String name = path.getFileName().toString();
        int lastIndex = name.lastIndexOf('.');
        if (lastIndex > 0 && lastIndex < name.length() - 1) {
            return name.substring(lastIndex + 1).toLowerCase();
        }
        return "";
    }
}
