package com.jsp.SmartFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.SmartFarm.entity.Image;
import com.jsp.SmartFarm.service.ImageService;
import com.jsp.SmartFarm.util.ResponseStructure;

@RestController
public class ImageController {
	
	@Autowired
	private ImageService imageService;

//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	postMapping (for save)
	
	@PostMapping("/imageUpload")
	public ResponseEntity<ResponseStructure<Image>> saveImage(@RequestParam int id, @RequestParam MultipartFile file) {
		return imageService.updateProfilePic(id, file);
	}
	
	@DeleteMapping("/imageDelete")
	public ResponseEntity<ResponseStructure<Image>> deleteImage(@RequestParam int id) {
		return imageService.deleteProfilePic(id);
	}
	
	@GetMapping("/imageFetch")
	public ResponseEntity<byte[]> fetchImage(@RequestParam int id) {
		return imageService.fetchImage(id);
	}
}
