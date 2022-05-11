package tn.esprit.spring.serviceInterface;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Image;

public interface IImageService {

	public abstract Image addImage(MultipartFile file) throws IOException;
	
	public abstract void deleteImage(Long id);

	public abstract Image getImageDetailsByName(String name);
	
	Optional<Image> retrieveImageByName(String name);
	
	Image getImageDetailsById(Long id);
}
