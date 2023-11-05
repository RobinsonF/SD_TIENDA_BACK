package com.BO.TiendaVirtualSB;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/images")
public class ImageController {

    @GetMapping("/{imageName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            String imageResourcePath = "static/images/" + imageName;
            Resource resource = new ClassPathResource(imageResourcePath);
            if (resource.exists() && resource.isReadable()) {
                MediaType contentType = determineContentType(imageName);
                return ResponseEntity.ok()
                        .contentType(contentType)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    private MediaType determineContentType(String imageName) {
        String lowerCaseName = imageName.toLowerCase();
        if (lowerCaseName.endsWith(".jpg") || lowerCaseName.endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG;
        } else if (lowerCaseName.endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        } else if (lowerCaseName.endsWith(".gif")) {
            return MediaType.IMAGE_GIF;
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}

