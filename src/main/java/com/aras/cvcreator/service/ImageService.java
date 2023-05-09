package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image save(Image image);

    Image uploadImage(MultipartFile file, String name);

    byte[] getImage(String name);

    Image getByName(String name);

    void delete(Image image);
}
