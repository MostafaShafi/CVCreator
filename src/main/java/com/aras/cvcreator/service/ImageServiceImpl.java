package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Image;
import com.aras.cvcreator.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository repository;

    @Override
    @Transactional
    public Image save(Image image) {
        return null;
    }

    @Override
    @Transactional
    public Image uploadImage(MultipartFile file, String name) {
        try {
            return repository.save(Image.builder().name(name).type("png").image(file.getBytes()).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public byte[] getImage(String name) {
        return repository.findByName(name).get().getImage();
    }

    @Override
    @Transactional
    public Image getByName(String name) {
        Optional<Image> dbImage = repository.findByName(name);
        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(dbImage.get().getImage()).build();

    }

    @Override
    @Transactional
    public void delete(Image image) {
        repository.delete(image);
    }
}
