package ru.volodin.SarComp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.volodin.SarComp.service.PhotoService;

import java.util.UUID;

@RestController
@RequestMapping("/sarcomp/photos")
public class PhotoController {

    private static final String HEADER_CONTENT_DISP = "inline; filename=\"%s\"";

    @Autowired
    private PhotoService photoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addPhoto(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(photoService.savePhotos(file));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{photoId}")
    public ResponseEntity<?> getPhoto(@PathVariable(value = "photoId") UUID photoId) {
        try {
            Resource resource = photoService.getPhotoById(photoId);
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, String.format(HEADER_CONTENT_DISP, resource.getFilename()))
                    .header(HttpHeaders.CONTENT_TYPE, photoService.getMimetypeFromFilename(resource.getFilename()))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{photoId}")
    public ResponseEntity<?> deletePhotoById(@PathVariable("photoId") UUID photoId) {
        try {
            photoService.deletePhotoById(photoId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
