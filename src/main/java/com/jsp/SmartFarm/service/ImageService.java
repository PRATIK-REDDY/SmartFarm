package com.jsp.SmartFarm.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.SmartFarm.dao.ImageDao;
import com.jsp.SmartFarm.dao.UserDao;
import com.jsp.SmartFarm.entity.Image;
import com.jsp.SmartFarm.entity.User;
import com.jsp.SmartFarm.exception.ImageUploadException;
import com.jsp.SmartFarm.exception.UserNotFound;
import com.jsp.SmartFarm.util.ResponseStructure;

@Service
public class ImageService {

	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private UserDao userDao;
	
	
	public ResponseEntity<byte[]> fetchImage(int id) {
		Image db = imageDao.fetchImageDao(id);
		
		if (db != null) {
			byte[] imageData = db.getData();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
		} else {
			throw new ImageUploadException();
		}
	}
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public ResponseEntity<ResponseStructure<Image>> updateProfilePic(int userId, MultipartFile file) {
		
		ResponseStructure<Image> m = new ResponseStructure<Image>();
		
		User db = userDao.fetchDao(userId);
		
		if (db != null) {
			Image image = db.getImage();
			
			if (image == null) {
				image = new Image();
			}
			
			image.setName(file.getOriginalFilename());
			try {
				image.setData(file.getBytes());
			} catch (IOException e) {
				throw new ImageUploadException();
			}
			
			db.setImage(image);
			userDao.updateDao(db);
			m.setData(image);
			m.setMessage("Image Uploaded successfully...");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Image>>(m, HttpStatus.OK);
		} else {
			throw new UserNotFound();
		}
		
	}
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public ResponseEntity<ResponseStructure<Image>> deleteProfilePic(int id) {
		
		ResponseStructure<Image> m = new ResponseStructure<Image>();
		
		User db = userDao.fetchDao(id);
		
		if (db != null) {
			Image img = imageDao.deleteDao(id);
			
			m.setData(img);
			m.setMessage("Image Deleted successfully...");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Image>>(m, HttpStatus.OK);
		} else {
			throw new UserNotFound();
		}
	}
}
