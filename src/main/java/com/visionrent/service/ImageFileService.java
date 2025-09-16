package com.visionrent.service;

import com.visionrent.domain.ImageData;
import com.visionrent.domain.ImageFile;
import com.visionrent.dto.ImageFileDTO;
import com.visionrent.exception.ResourceNotFoundException;
import com.visionrent.exception.message.ErrorMessage;
import com.visionrent.repository.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageFileService {

    @Autowired
    private ImageFileRepository imageFileRepository;

    public UUID saveImage(MultipartFile file) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        ImageFile imageFile;
        try {
            ImageData imData = new ImageData(file.getBytes());
            imageFile = new ImageFile(fileName, file.getContentType(), imData);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        imageFileRepository.save(imageFile);
        return imageFile.getId();

    }

    public ImageFile getImageById(UUID id) {

        return imageFileRepository.findById(id).orElseThrow(() ->
               new ResourceNotFoundException(String.format(ErrorMessage.IMAGE_NOT_FOUND_MESSAGE, id)));

    }

    public List<ImageFileDTO> getAllImages() {
        List<ImageFile> imageFiles = imageFileRepository.findAll();

        List<ImageFileDTO> imageFileDTOs = imageFiles.stream().map(imFile -> {
            String imageIdAsString = imFile.getId().toString();

            String imageUri = ServletUriComponentsBuilder
                              .fromCurrentContextPath() // http://localhost:8080
                              .path("/files/download/") // http://localhost:8080/files/download/
                              .path(imageIdAsString )   // http://localhost:8080/files/download/b7b4a2e0-...
                              .toUriString();
            return new ImageFileDTO(imFile.getName(), imageUri, imFile.getType(), imFile.getLength());
        }).collect(Collectors.toList());
        return imageFileDTOs;
    }

    public void removeById(UUID id) {
        ImageFile imageFile = getImageById(id);
        imageFileRepository.delete(imageFile);
    }

    // car tarafı için gerekli method
    public ImageFile findImageById(UUID id) {

       return imageFileRepository.findImageById(id).orElseThrow(() ->
                new  ResourceNotFoundException(String.format(ErrorMessage.IMAGE_NOT_FOUND_MESSAGE, id)));
    }
}
