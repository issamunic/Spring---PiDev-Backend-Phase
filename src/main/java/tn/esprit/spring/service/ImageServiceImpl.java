package tn.esprit.spring.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Image;
import tn.esprit.spring.imageConfig.ImageUtility;
import tn.esprit.spring.repository.ImageRepository;
import tn.esprit.spring.serviceInterface.IImageService;

@Service
@Slf4j
public class ImageServiceImpl implements IImageService{
	
	@Autowired
	ImageRepository imageRepository;


	@Override
	public Image addImage(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		return imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
	}

	@Override
	public void deleteImage(Long id) {
		imageRepository.deleteById(id);
	}

	@Override
	public Image getImageDetailsByName(String name) {
		final Optional<Image> dbImage = imageRepository.findByName(name);
        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
	}
	
	@Override
	public Image getImageDetailsById(Long id) {
		final Optional<Image> dbImage = imageRepository.findById(id);
        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
	}

	@Override
	public Optional<Image> retrieveImageByName(String name) {
		return imageRepository.findByName(name);
	}
}
