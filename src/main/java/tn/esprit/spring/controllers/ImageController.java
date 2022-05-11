package tn.esprit.spring.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import tn.esprit.spring.entities.Image;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.imageConfig.ImageUploadResponse;
import tn.esprit.spring.imageConfig.ImageUtility;
import tn.esprit.spring.repository.ImageRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.JwtRequestFilter;
import tn.esprit.spring.serviceInterface.IImageService;

@RestController
//@CrossOrigin(origins = "http://localhost:8082") open for specific port
@CrossOrigin
public class ImageController {

	@Autowired
    ImageRepository imageRepository;
	
	@Autowired
	IImageService imageService;
	
	@Autowired
	JwtRequestFilter JwtRequestFilter;
	
	@Autowired
	UserRepository userRepository;

	
	/*@PostMapping("/image/upload")
    public ResponseEntity<ImageUploadResponse> uplaodImage0(@RequestParam("image") MultipartFile file) throws IOException {
    	imageService.addImage(file);
        return ResponseEntity
        		.status(HttpStatus.OK)
        		.body(new ImageUploadResponse("Image uploaded successfully: " +file.getOriginalFilename()));
    }*/
	
	
	//upload and affect image to user
    @PostMapping("/image/upload")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file) throws IOException {
    	Image image = imageService.addImage(file);
    	User currentUser=JwtRequestFilter.getCurrentUser();
    	currentUser.setImage(image);
    	userRepository.save(currentUser);
        return ResponseEntity
        		.status(HttpStatus.OK)
        		.body(new ImageUploadResponse("Image uploaded successfully: " +file.getOriginalFilename()+" , idImage : "+image.getIdImage()));
    }
    
    
    @GetMapping(path = {"/image/get/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {
    	return imageService.getImageDetailsByName(name);
    }

    
    @GetMapping(path = {"/image/get/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageService.retrieveImageByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
    
    @DeleteMapping(path = {"/image/delete/{id}"})
    public ResponseEntity<String> DropImage(@PathVariable("id") Long idImage){
    	imageService.deleteImage(idImage);
    		return ResponseEntity
    			.status(HttpStatus.OK)
    			.body("image deleted successfully");
    }
}
