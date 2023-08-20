package com.example.marketplace.controllers;

import com.example.marketplace.model.Picture;
import com.example.marketplace.repositories.PictureRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PictureController {

    private final PictureRepository pictureRepository;

    @GetMapping("/pictures/{id}")
    public ResponseEntity<?> getPictureById(@PathVariable Long id) {
        Optional<Picture> optionalPicture = pictureRepository.findById(id);
        if (optionalPicture.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Picture picture = optionalPicture.get();
        return ResponseEntity.ok().header("fileName", picture.getFileName())
                .contentType(MediaType.valueOf(picture.getContentType()))
                .contentLength(picture.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(picture.getBytes())));
    }

}
